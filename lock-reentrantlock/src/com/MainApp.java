package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
	
	ReentrantLock locker;
	Condition condition;
	
	public Worker(){
		this.locker = new ReentrantLock();
		this.condition = locker.newCondition();
	}
	
	public void producer() throws InterruptedException{
		locker.lock();
		try {
			System.out.println(" - On producer() 1...");
			condition.await(); //wait until there is another thread call condition.signal();
			
			System.out.println(" - On producer() 2...");
		}
		finally {
			locker.unlock();
		}
	}
	
	public void consumer() throws InterruptedException {		

		Thread.sleep(2000);
		
		
		locker.lock();
		try {
			System.out.println("\t On consumer() 1...");
			condition.signal();
			
			System.out.println("\t On consumer() 2...");
			Thread.sleep(2000);
			
			System.out.println("\t On consumer() 3...");
		}
		finally {
			locker.unlock();
		}
	}
	
}

public class MainApp {

	public static void main(String[] args) {
		Worker worker = new Worker();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();

	}

}
