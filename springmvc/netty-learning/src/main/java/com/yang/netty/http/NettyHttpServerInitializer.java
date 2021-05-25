package com.yang.netty.http;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class NettyHttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        /**
         * 向管道加入处理器
         * 先得到管道
         */
        ChannelPipeline pipeline = ch.pipeline();

        /**
         * 加入一个Netty提供的HttpServerCodeC codec -> {coder - decoder}
         * 1. HttpServerCodec是Netty提供的处理http的编解码器
         */
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        /**
         * 2. 增加一个自定义的handler
         */
        pipeline.addLast("MyTestHttpServerHandler", new NettyHttpServerHandler());
    }
}
