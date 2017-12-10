package com.zhuangqf.learn.netty.server;

import java.io.IOException;
import java.util.Scanner;

public class EchoServerTest {

    public static void main(String[] arg) throws IOException {
        EchoServer server = new EchoServer();
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