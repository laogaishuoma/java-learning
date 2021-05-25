package com.yang.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class IOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(6000);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);

        /**
         * 创建Buffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            int readcount = 0;
            while (-1 != readcount) {
                try {
                    readcount = socketChannel.read(byteBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                /**
                 * 倒带 position = 0 mark作废
                 */
                byteBuffer.rewind();
            }
        }
    }
}
