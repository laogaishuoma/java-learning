package com.yang.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioMappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raFile = new RandomAccessFile("/Users/qiaoguoqiang/workspace/learning/1.txt", "rw");
        // 获取对应通道
        FileChannel channel = raFile.getChannel();

        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 使用读写模式
         * 参数2: 0, 可以直接修改的起始位置
         * 参数3: 5, 是映射到内存的大小(不是索引位置), 即将1.txt的多少个字节映射到内存
         * 这里创建的MappedByteBuffer: 可以直接修改的范围就是0~5
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '4');
        // mappedByteBuffer.put(5, (byte) 'Y'); // IndexOutBoundsException

        raFile.close();
        System.out.println("修改成功~~~");
    }
}
