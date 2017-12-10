package com.zhuangqf.learn.netty.client;

import com.zhuangqf.learn.netty.handler.EventLoggerHandler;
import com.zhuangqf.learn.netty.handler.PrintHandler;

import java.io.IOException;

public class DefaultClientTest {

    public static void main(String[] args) throws IOException {
        DefaultClient client = new DefaultClient("localhost");
        client.addHandler(EventLoggerHandler.class);
        client.addHandler(PrintHandler.class);
        client.run();
        client.send("hello world");
        client.close();
    }

}