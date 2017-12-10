package com.zhuangqf.learn.netty.server;

import com.zhuangqf.learn.netty.handler.EchoHandler;
import com.zhuangqf.learn.netty.handler.EventLoggerHandler;

/**
 * @author zhuangqf
 */
public class EchoServer  extends DefaultServer{

    EchoServer(){
        this.addHandler(EventLoggerHandler.class);
        this.addHandler(EchoHandler.class);
    }

}
