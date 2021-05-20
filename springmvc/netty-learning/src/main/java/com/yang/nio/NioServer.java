package com.yang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {
        /**
         * 1. 创建ServerSocketChannel
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        /**
         * 2. 获取一个Seletor对象
         */
        Selector selector = Selector.open();

        /**
         * 3. 绑定端口6666， 在服务器端监听
         */
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        /**
         * 4. 设置为非阻塞
         */
        serverSocketChannel.configureBlocking(false);

        /**
         * 5. 把ServerSocketChannel注册到Selector
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        /**
         * 6. 循环等待客户端连接
         */
        while(true) {
            /**
             * 这里我们等待1秒，如果没有事件发生，返回
             */
            if(selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            /**
             * 如果返回的>0, 就获取得到相关的SelectionKey集合
             * 1. 如果返回的 > 0, 表示已经获取到关注的事件
             * 2. selector.selectedKeys()返回关注事件的集合
             * 通过selectionKeys反向获取通道
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            /**
             * 遍历seletionKey, 使用迭代器遍历
             */
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                /**
                 * 根据Key对应的通道发生的事件做相应处理
                 */
                if (key.isAcceptable()) {
                    /**
                     * 有新的客户端连接
                     * 为该客户端生成一个SocketChannel
                     */
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个socketChannel " + socketChannel.hashCode());
                    /**
                     * ----------------------
                     * 将SocketChannel设置为非阻塞
                     * ----------------------
                     */
                    socketChannel.configureBlocking(false);
                    /**
                     * 1. 将当前SocketChannel注册到selector
                     * 2. 设置关注事件为OP_READ
                     * 3. 同时给SocketChannel关联一个Buffer
                     */
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {
                    /**
                     * 发生OP_READ事件
                     * 通过key反向获取到对应channel
                     */
                    SocketChannel channel = (SocketChannel) key.channel();
                    /**
                     * 获取到该channel关联的buffer
                     */
                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    channel.read(buffer);
                    System.out.println("客户端发送数据: " + new String(buffer.array()));
                }

                /**
                 * 手动从集合中移除当前的SelectionKey, 防止重复操作
                 */
                keyIterator.remove();
            }
        }
    }
}
