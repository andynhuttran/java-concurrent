package com.andynhuttran;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DataHolder {
    boolean flag;
    int num;
    String threadName;

    public DataHolder(boolean flag, int num, String threadName) {
        this.flag = flag;
        this.num = num;
        this.threadName = threadName;
    }


    @Override
    public String toString() {
        return "DataHolder{" +
                "flag=" + flag +
                ", num=" + num +
                ", threadName='" + threadName + '\'' +
                '}';
    }
}

class ThreadSafeObject {

    //Java before 8
    public static ThreadLocal<DataHolder> dataHolderBeforeJava8 = new ThreadLocal<DataHolder>(){
        @Override
        protected DataHolder initialValue() {
            return new DataHolder(true, 7, Thread.currentThread().getName());
        }

        @Override
        public DataHolder get() {
            return super.get();
        }
    };

    public static ThreadLocal<DataHolder> dataHolderAfterJava8 = ThreadLocal.withInitial(() -> {
        return new DataHolder(false, 8, Thread.currentThread().getName());
    });

}



public class EachThreadHasEachObject implements  Runnable{

    //make sure one thread has only one object DataHolder
    public DataHolder getHolder(){
        return ThreadSafeObject.dataHolderAfterJava8.get();
    }
    @Override
    public void run() {
        String currentThread = Thread.currentThread().getName();
        this.getHolder().num++;
        System.out.println(currentThread + ": " + this.getHolder().toString());
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        EachThreadHasEachObject eachThreadHasEachObject = new EachThreadHasEachObject();
        for (int i = 0; i < 10; ++i) {
            executorService.execute(eachThreadHasEachObject);
        }

        executorService.shutdown();
    }


}





