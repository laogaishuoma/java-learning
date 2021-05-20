package com.yang.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileChannel {
    public static void main(String[] args) throws IOException {
        String str = "Hello Yang";
        // 创建一个输出流， 包装到Channel中
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/qiaoguoqiang/workspace/learning/file01.txt");

        /**
         * 通过fileOutPutStream获取对应的FileChannel
         * 这个fileChannel真实类型是FileChannelImpl
         */
        FileChannel fileChannel = fileOutputStream.getChannel();

        /**
         * 创建一个缓冲区ByteBuffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        // 对byteBuffer进行反转
        byteBuffer.flip();

        // 将byteBuffer的数据写入到channel: fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
