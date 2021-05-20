package com.yang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {
        /**
         * 1. 得到一个网络通道
         */
        SocketChannel socketChannel = SocketChannel.open();

        /**
         * 2. 设置非阻塞
         */
        socketChannel.configureBlocking(false);

        /**
         * 提供服务端的IP和端口
         */
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        /**
         * 3. 连接服务器
         */
        if (!socketChannel.connect(inetSocketAddress)) {
            while(!socketChannel.finishConnect()) {
                System.out.println("因为连接服务器需要时间，客户端不会阻塞，可以做其他工作...");
            }
        }

        /**
         * 4. 如果连接成功，就发送数据
         */
        String str = "Hello 山路海涯";
        /**
         * 包装字节数组到buffer
         */
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());

        /**
         * 5. 发送数据，将buffer下入channel
         */
        socketChannel.write(buffer);

        // 读取一下，让客户端停在这里
        System.in.read();
    }
}
