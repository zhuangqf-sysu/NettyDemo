package com.zhuangqf.learn.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangqf
 */
public abstract class AbstractEndpoint implements Runnable,Closeable{

    protected int port = 8080;
    protected Class<? extends ChannelInitializer<? extends Channel>> initializerClass = DefaultChannelInitializer.class;
    protected List<Class<? extends ChannelHandler>> handlerClassList = new ArrayList<>();

    public void setPort(int port){
        this.port = port;
    }

    public void setInitializerClass( Class<? extends ChannelInitializer<? extends Channel>> initializerClass) {
        this.initializerClass = initializerClass;
    }

    public void addHandler(Class<? extends ChannelHandler> clazz){
        this.handlerClassList.add(clazz);
    }

    private class DefaultChannelInitializer extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            for (Class<? extends ChannelHandler> clazz : handlerClassList) {
                ChannelHandler handler = clazz.getDeclaredConstructor().newInstance();
                channel.pipeline().addLast(handler);
            }
        }
    }

}
