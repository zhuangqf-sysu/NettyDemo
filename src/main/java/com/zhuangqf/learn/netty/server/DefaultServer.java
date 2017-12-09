package com.zhuangqf.learn.netty.server;

import com.zhuangqf.learn.netty.AbstractEndpoint;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author zhuangqf
 */
public class DefaultServer extends AbstractEndpoint implements Runnable,Closeable{

    private static final Logger logger = LoggerFactory.getLogger(DefaultServer.class);
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private Channel channel;

    @Override
    public void run() {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        for (Class<? extends ChannelHandler> clazz : handlerClassList) {
                            ChannelHandler handler = clazz.getDeclaredConstructor().newInstance();
                            channel.pipeline().addLast(handler);
                        }
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        channel = bootstrap.bind(port).addListener(connectFuture->logger.info("[server start:"+port+"]->"
                +connectFuture.isSuccess())).channel();
    }

    @Override
    public void close() throws IOException {
        channel.close().addListener(channelFuture->{
            logger.info("[server close channel]->"+channelFuture.isSuccess());
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("[server shutdown]");
        });
    }
}
