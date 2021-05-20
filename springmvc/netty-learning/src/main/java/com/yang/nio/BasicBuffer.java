package com.yang.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        // 举例说明Buffer的使用
        // 创建一个Buffer, 大小为5，即可存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 向buffer中存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put( i * 2 );
        }

        /**
         * 如何从buffer读取数据
         * 将buffer转换、读写切换
         * 该操作主要是重置buffer中的几个标志位
         */
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
