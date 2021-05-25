package com.yang.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断msg是不是HttpRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline hashcode: " + ctx.pipeline().hashCode() + " NettyHttpServerHandler hash = " + this.hashCode());

            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址 =" + ctx.channel().remoteAddress());

            // 获取到HttpRequest
            HttpRequest httpRequest = (HttpRequest) msg;
            // 获取uri, 过滤制定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了favicon.ico, 不做响应");
                return ;
            }
            // 回复信息给浏览器[Http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

            /**
             * 构造一个http的响应，即HttpResponse
             */
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            response.headers().set(HttpHeaderNames.ACCEPT_CHARSET, CharsetUtil.UTF_8);
            /**
             * 构建好response返回
             */
            ctx.writeAndFlush(response);
        }
    }
}
