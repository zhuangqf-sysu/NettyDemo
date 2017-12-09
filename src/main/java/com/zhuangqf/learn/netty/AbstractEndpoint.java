package com.zhuangqf.learn.netty;

import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuangqf
 */
public abstract class AbstractEndpoint{

    protected int port = 8080;

    protected List<Class<? extends ChannelHandler>> handlerClassList = new ArrayList<>();

    public void setPort(int port){
        this.port = port;
    }

    public void addHandler(Class<? extends ChannelHandler> clazz){
        this.handlerClassList.add(clazz);
    }

}
