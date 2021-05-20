package com.yang.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 聊天室服务器端
 * 1. 服务器启动并监听6667
 * 2. 服务器接收客户信息，并实现转发[上线和离线]
 */
public class NioChatServer {
    /**
     * 首先定义相关属性
     */

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    /**
     * 完成初始化工作
     */
    public NioChatServer() {
        try {
            /**
             * 1. 得到Selector
             */
            this.selector = Selector.open();
            /**
             * 2. ServerSocketChannel并绑定端口
             */
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            /**
             * 3. 设置为非阻塞
             */
            listenChannel.configureBlocking(false);
            /**
             * 4. 将ServerSocketChannel注册到Selector
             */
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            while(true) {
                int count = selector.select(2000);

                if (count > 0) {
                    /**
                     * 有事件处理
                     * 遍历得到SelectionKey集合
                     */
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                    while (iterator.hasNext()) {
                        /**
                         * 取出SelectionKey
                         */
                        SelectionKey key = iterator.next();

                        /**
                         * 监听到accept事件
                         */
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);

                            System.out.println(socketChannel.getRemoteAddress() + "上线了");
                        }

                        /**
                         * 监听到read事件， 处理读事件
                         */
                        if (key.isReadable()) {
                            readData(key);
                        }

                        /**
                         * 将当前key移除，防止重复操作
                         */
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读客户端数据的方法
     */
    private void readData(SelectionKey key) {
        /**
         * 定义一个SocketChannel
         */
        SocketChannel channel = (SocketChannel) key.channel();

        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);

            if (count > 0) {
                /**
                 * 把缓冲区的数据转成字符串
                 * 服务器打印了客户端的消息
                 */
                String msg = new String(buffer.array());
                System.out.println("from 客户端: " + msg);

                // 向其他的客户端端转发消息(不含自己)
                sendInfoToOtherClient(msg, channel);
            }
        } catch (IOException e) {
            // 提示客户离线
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了...");

                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }


    /**
     * 给其他客户端发送消息
     * @param msg 转发的消息
     * @param self 当前SocketChannel
     */
    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
        // 遍历注册到Selector上的SocketChannel, 并排除自己
        for (SelectionKey key : selector.keys()) {
            // 通过Key取出SocketChannel
            Channel targetChannel = key.channel();

            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                // 转型
                SocketChannel dest = (SocketChannel) targetChannel;

                // 将msg存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 将buffer写入通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        NioChatServer nioChatServer = new NioChatServer();
        nioChatServer.listen();
    }
}
