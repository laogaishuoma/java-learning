package com.yang.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HeartBeatServer {
    public static void main(String[] args) throws Exception {
        /**
         * 创建两个线程组
         */
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
                            /**
                             * 加入一个netty提供的IdleStateHandler
                             * 1. IdleStateHandler是netty提供的处理空闲状态的处理器
                             * 2. 参数介绍
                             *    long readerIdleTime: 表示多长时间没有读，就会发送一个心跳检测包检测是否连接
                             *    long writerIdleTime: 表示多长时间没有写，就会发送一个心跳检测包检测是否连接
                             *    long allIdleTime: 表示多长时间没有读写，就会发送一个心跳检测包检测是否连接
                             * 3. 通道有一段时间没有执行读、写、或者没有读写就触发一个IdleStateEvent事件。
                             * 4. 当IdleEvent触发后，就会传递给管道的下一个handler去处理
                             *    通过调用(触发)下一个handler的userEventTriggered, 在该方法中处理IdleStateEvent(读空闲、写空闲、读写空闲)
                             */
                            pipeline.addLast(new IdleStateHandler(13, 5, 2, TimeUnit.SECONDS));

                            pipeline.addLast(new MyServerHandler());
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
