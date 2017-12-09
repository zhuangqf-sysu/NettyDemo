package com.zhuangqf.learn.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RunnableServer implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(RunnableServer.class);

    private int port = 8080;
    private List<Class<? extends ChannelHandler>> handlerClassList = new ArrayList<Class<? extends ChannelHandler>>();

    public void setPort(int port){
        this.port = port;
    }

    public void addHandler(Class<? extends ChannelHandler> clazz){
        this.handlerClassList.add(clazz);
    }

    @Override
    public void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            for(Class clazz: handlerClassList){
                                 ChannelHandler handler = (ChannelHandler) clazz.getDeclaredConstructor().newInstance();
                                 channel.pipeline().addLast(handler);
                            }
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            logger.info("server start");
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException ignored) {
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("server shutdown");
        }
    }
}
