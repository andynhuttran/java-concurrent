package com.andynhuttran;

import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner {

    //private boolean allowToRun = false;
    private AtomicInteger atomicInteger = new AtomicInteger(0);



    public static void main(String[] args) throws InterruptedException {

        TaskRunner taskRunner = new TaskRunner();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                int result = taskRunner.atomicInteger.addAndGet(1);
                System.out.println("t1: " + result);
            }

        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                int result = taskRunner.atomicInteger.addAndGet(1);
                System.out.println("t2: " + result);
            }
        });

        t1.start();
        t2.start();


        //wait t1 and t2 done
        t1.join();
        t2.join();

        System.out.println(taskRunner.atomicInteger.get());

    }

}
