package com.zhuangqf.learn.netty.client;

import com.zhuangqf.learn.netty.AbstractEndpoint;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhuangqf
 */
public class DefaultClient extends AbstractEndpoint implements Runnable,Closeable{

    private static final Logger logger = LoggerFactory.getLogger(DefaultClient.class);

    private EventLoopGroup workerGroup;
    private Channel channel;
    private String host;

    DefaultClient(String host){
        this.host = host;
    }

    @Override
    public void run() {
        workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(initializerClass.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return;
        }
        ChannelFuture channelFuture =  bootstrap.connect(host,port);
        channelFuture.addListener(future -> logger.info("client connect with "+host+":"+port+"->"+future.isSuccess()));
        channel = channelFuture.channel();
    }

    void send(String message){
        channel.writeAndFlush(channel.alloc().buffer(message.length()).writeBytes(message.getBytes())).addListener(future-> logger.info("client send message:"+message+"->"+future.isSuccess()));
    }

    @Override
    public void close() throws IOException {
        channel.close().addListener(future-> {
            logger.info("client disconnect with "+host+":"+port+"->"+future.isSuccess());
            workerGroup.shutdownGracefully().addListener(shutdownFuture-> logger.info("client shutdown->"+shutdownFuture.isSuccess()));
        });
    }
}
