package com.zhuangqf.learn.netty.server;

import com.zhuangqf.learn.netty.AbstractEndpoint;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuangqf
 */
public class DefaultServer extends AbstractEndpoint implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(DefaultServer.class);

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
