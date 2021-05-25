package com.yang.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class IOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 6000));

        String filename = "/Users/qiaoguoqiang/Downloads/素材/视频/美食.mp4";
        // 得到一个文件Channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        // 准备发送
        long startTime = System.currentTimeMillis();

        /**
         * 在Linux下一个transferTo方法就可以完成传输
         * 在Windows下一次调用transferTo只能发送8M, 就需要分段传输文件
         * transferTo底层使用到零拷贝
         */
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总的字节数 = " + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
