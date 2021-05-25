package com.yang.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义一个Handler, 需要继承Netty规定好的某个HandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据
     * 这里我们可以读取客户端发送的消息
     * @param ctx 上下文对象，含有pipeline, channel, 地址
     * @param msg 就是客户端发送的数据，默认Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server ctx = " + ctx);
        // 将msg转成一个ByteBuffer, ByteBuf是Netty提供的， 不是NIO的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息是: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址: " + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**
         * 将数据写入缓存并刷新
         * 一般来说，对发送的数据进行编码
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, client: (>^ω^<)喵", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
