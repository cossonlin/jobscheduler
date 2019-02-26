package com.taiger.isearch.task;

import java.time.Instant;

public class Task2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Run Task2 at " + Instant.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task2 completed");
    }
}