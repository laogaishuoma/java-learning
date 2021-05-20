package com.yang.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 我们只实现服务端，客户端使用telnet来模拟
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        /**
         * 使用线程池机制
         * 思路:
         * 1. 创建一个线程池
         * 2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");
        while(true) {
            // 监听，等待客户端连接
            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // 可以和客户端通讯
                    handler(socket);
                }
            });
        }
    }

    // 编写一个handler方法，和客户端通讯
    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            // 输出当前线程信息
            Thread thread = Thread.currentThread();
            System.out.println("线程信息 id = " + thread.getId() + ", name = " + thread.getName());
            InputStream inputStream = socket.getInputStream();
            // 循环读取客户端发送的数据
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输出客户端发送的数据
                    System.out.println(thread.getName() + ":" + new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket连接
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
