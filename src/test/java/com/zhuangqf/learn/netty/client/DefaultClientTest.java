package com.zhuangqf.learn.netty.client;

import com.zhuangqf.learn.netty.handler.EventLoggerHandler;
import com.zhuangqf.learn.netty.handler.PrintHandler;

import java.util.Scanner;

public class DefaultClientTest {

    public static void main(String[] args){
        DefaultClient client = new DefaultClient("localhost");
        client.addHandler(EventLoggerHandler.class);
        client.addHandler(PrintHandler.class);
        client.run();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.nextLine();
            client.send(message);
        }
    }

}