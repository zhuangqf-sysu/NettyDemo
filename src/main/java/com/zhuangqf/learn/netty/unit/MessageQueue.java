package com.zhuangqf.learn.netty.unit;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhuangqf
 */
public class MessageQueue {

    private static final ConcurrentLinkedQueue<String> MESSAGE_QUEUE = new ConcurrentLinkedQueue<>();

    public static String get(){
        if(!MESSAGE_QUEUE.isEmpty()) {
            return MESSAGE_QUEUE.poll();
        } else {
            return null;
        }
    }

    public static void put(String message){
        MESSAGE_QUEUE.offer(message);
    }

}
