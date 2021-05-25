package com.yang.zerocopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NormalCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/qiaoguoqiang/Downloads/素材/视频/美食.mp4");
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/qiaoguoqiang/workspace/learning/1.mp4");
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        // 准备发送
        long startTime = System.currentTimeMillis();

        while(true) {
            /**
             * 这里有一个重要的操作，一定不要忘记了
             *
             * 清空我们的buffer
             * 如果不clear(), 文件内容太短， 读取完后postion和limit相等， read()总是返回0， 永远都不可能得到-1。
             */
            byteBuffer.clear();

            int read = fileInputStreamChannel.read(byteBuffer);
            if (read == -1) {
                System.out.println("耗时:" + (System.currentTimeMillis() - startTime));
                break;
            }
            // 将buffer中的数据写入到fileOutputStreamChannel
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);
        }
    }
}