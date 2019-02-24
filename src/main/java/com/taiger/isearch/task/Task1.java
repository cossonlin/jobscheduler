package com.taiger.isearch.task;

public class Task1 implements Runnable{
    @Override
    public void run() {
        System.out.println("Run Task1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task1 completed");
    }

    public int getConnectorId(){
        return 1;
    }
}
