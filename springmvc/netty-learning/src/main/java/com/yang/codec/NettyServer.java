package com.yang.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * @author qiaoguoqiang
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 1. 创建两个线程组: bossGroup和wokerGroup
         *   - bossGroup: 只处理连接请求
         *   - workerGroup: 执行真正和客户端的业务处理
         *   两者都是无限循环
         *
         *   两者默认含有子线程(NioEventLoop)的个数为: 实际CPU核数 * 2
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * 2. 创建服务器端的启动对象，配置参数
             */
            ServerBootstrap bootstrap = new ServerBootstrap();

            /**
             * 使用链式编程来进行设置
             * 1. 设置两个线程组
             * 2. 使用NioSocketChannel作为服务器的通道实现
             * 3. 设置线程队列得到的连接个数
             * 4. 设置保持活动连接状态
             * 5. 创建一个普通测试对象(匿名对象)
             * 6. 给pipeline设置处理器: 给workerGroup的EventLoop对应的管道设置处理器
             */
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("...服务器 is ready ...");

            /**
             * 绑定一个端口并且同步，生成了一个ChannelFuture对象
             */
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("监听端口 6668 成功");
                    } else {
                        System.out.println("监听端口 6668 失败");
                    }
                }
            });
            /**
             * 对关闭通道进行监听
             */
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
