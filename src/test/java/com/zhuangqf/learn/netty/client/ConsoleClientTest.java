package com.zhuangqf.learn.netty.client;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleClientTest {

    public static void main(String[] arg) throws IOException {
        ConsoleClient client = new ConsoleClient();
        client.run();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.nextLine();
            if("exit".equals(message)){
                client.close();
                break;
            }else {
                client.send(message);
            }
        }
    }

}