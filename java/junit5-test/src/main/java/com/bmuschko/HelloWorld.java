package com.bmuschko;

import com.bmuschko.executor.ListeningExecutor;
import com.bmuschko.messenger.Messenger;

public class HelloWorld {
    public static void main(String[] args) {
        Messenger messenger = new Messenger();
        ListeningExecutor executor = new ListeningExecutor();

        System.out.println(messenger.getMessage());
        System.out.println(executor.getRandomDoubleValue());
        executor.closeExecutor();
    }
}