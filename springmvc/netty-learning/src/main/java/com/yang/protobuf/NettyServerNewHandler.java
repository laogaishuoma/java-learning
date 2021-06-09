package com.yang.protobuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 自定义一个Handler, 需要继承Netty规定好的某个HandlerAdapter
 */
public class NettyServerNewHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage message) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = message.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.WorkerType) {
            MyDataInfo.Worker workder = message.getWorkder();
            System.out.println("客户端发送的数据: 工人 名字 = " + workder.getName() + " 年龄 = " + workder.getAge());
        } else if (dataType == MyDataInfo.MyMessage.DataType.StudentType) {
            MyDataInfo.Student student = message.getStudent();
            System.out.println("客户端发送的数据: 学生 ID = " + student.getId() + " 名字 = " + student.getName());
        } else {
            System.out.println("客户端发送的数据格式不对");
        }
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
