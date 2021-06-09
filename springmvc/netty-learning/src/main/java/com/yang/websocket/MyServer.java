package com.yang.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 因为基于HTTP协议，使用HTTP的编解码
                            pipeline.addLast(new HttpServerCodec());
                            // 以块方式写，添加ChunkedWriteHandler
                            pipeline.addLast(new ChunkedWriteHandler());
                            /**
                             * 1. Http数据在传输过程中是分段的，HttpObjectAggregator就是可以将多个段聚合
                             * 2. 这就是为什么，当浏览器发送大量数据时，就会发出多个http请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /**
                             * 1. 对于WebSocket, 它的数据以桢(frame)形式传递
                             * 2. 可以看到WebSocketFrame下面有六个子类
                             * 3. 浏览器请求时: ws://localhost:7000/hello 表示URI
                             * 4. WebSocketServerProtocolHandler核心功能是将http协议升级为ws协议，保持长连接
                             * 5. 是通过101状态码做协议切换的
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
