package com.zhuangqf.learn.netty.client;

import com.zhuangqf.learn.netty.handler.EventLoggerHandler;
import com.zhuangqf.learn.netty.handler.PrintHandler;

public class ConsoleClient extends DefaultClient {

    ConsoleClient(){
        this("localhost");
    }

    ConsoleClient(String hostname){
        super(hostname);
        this.addHandler(EventLoggerHandler.class);
        this.addHandler(PrintHandler.class);

    }

}
