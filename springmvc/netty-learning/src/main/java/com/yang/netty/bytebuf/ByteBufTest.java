package com.yang.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
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

    @Test
    public void test02() {
        // 1. 创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world!", CharsetUtil.UTF_8);

        // 2. 使用相关方法
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();

            // 将content转换成字符串
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf = " + byteBuf);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            System.out.println(byteBuf.getByte(0));

            int len = byteBuf.readableBytes();
            System.out.println("len = " + len);

            // 使用for取出各个字节
            for (int i = 0; i < len; i++) {
                System.out.println((char) byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(0, 4, CharsetUtil.UTF_8));
            System.out.println(byteBuf.getCharSequence(4, 6, CharsetUtil.UTF_8));
        }
    }
}
