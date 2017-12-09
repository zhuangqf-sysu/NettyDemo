package com.zhuangqf.learn.netty.handler;

import com.zhuangqf.learn.netty.server.DefaultServer;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class EchoHandlerTest {

    public static void main(String[] arg) throws IOException {
        DefaultServer server = new DefaultServer();
        server.addHandler(EventLoggerHandler.class);
        server.addHandler(EchoHandler.class);
        server.run();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if("exit".equals(command)){
                server.close();
                break;
            }
        }
    }

}