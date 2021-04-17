package com.andynhuttran;


public class TaskRunner {

    //private boolean allowToRun = false;
    private volatile boolean allowToRun = false;



    public static void main(String[] args) {

        TaskRunner taskRunner = new TaskRunner();

        Thread t1 = new Thread(() -> {
            System.out.println("Hello 1");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            taskRunner.allowToRun = true;
            System.out.println("End Hello 1 said allowToRun = " + taskRunner.allowToRun);

        });

        Thread t2 = new Thread(() -> {
            while (!taskRunner.allowToRun) {
                //System.out.println("taskRunner.allowToRun: " + taskRunner.allowToRun);
            };

            System.out.println("Hello 2");
        });

        t1.start();
        t2.start();

    }
}


