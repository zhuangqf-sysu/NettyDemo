package com.zhuangqf.learn.netty.handler;

import com.zhuangqf.learn.netty.server.RunnableServer;
import org.junit.Test;

public class EachHandlerTest {

    @Test
    public void run(){
        RunnableServer server = new RunnableServer();
       // server.addHandler(EventLoggerHandler.class);
        server.addHandler(EachHandler.class);
        server.run();
    }

}