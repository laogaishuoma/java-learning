package com.yang.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        // 向服务器发送数据
        socket.getOutputStream().write("Hello Server".getBytes());
        socket.getOutputStream().flush();

        System.out.println("向服务器发送数据结束");
        byte[] bytes = new byte[1024];

        // 接收服务端回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务器的数据:" + new String(bytes));
        socket.close();
    }
}
