package com.zhuangqf.learn.netty.unit;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhuangqf
 */
public class MessageQueue {

    private static final ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();

    public static String get(){
        if(!messageQueue.isEmpty()) {
            return messageQueue.poll();
        } else {
            return null;
        }
    }

    public static void put(String message){
        messageQueue.offer(message);
    }

}
