package com.taiger.isearch.task;

import java.time.Instant;

public class Task1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Run Task1 at " + Instant.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task1 completed");
    }
}