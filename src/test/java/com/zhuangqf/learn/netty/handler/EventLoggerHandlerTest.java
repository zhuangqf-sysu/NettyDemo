package com.zhuangqf.learn.netty.handler;

import com.zhuangqf.learn.netty.server.DefaultServer;
import org.junit.Test;


public class EventLoggerHandlerTest {

    @Test
    public void run(){
        DefaultServer server = new DefaultServer();
        server.addHandler(EventLoggerHandler.class);
        server.run();
    }

}