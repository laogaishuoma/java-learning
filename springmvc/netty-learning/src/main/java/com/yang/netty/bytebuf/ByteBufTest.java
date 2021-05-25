package com.yang.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class ByteBufTest {
    @Test
    public void test01() {
        /**
         * 1. 创建一个ByteBuf
         */
        Integer count = 10;
        ByteBuf buffer = Unpooled.buffer(count);
        for (int i = 0; i < count; i++) {
            buffer.writeByte(i);
        }
        System.out.println("capacity = " + buffer.capacity());

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }

        System.out.println("执行完毕");
    }
}
