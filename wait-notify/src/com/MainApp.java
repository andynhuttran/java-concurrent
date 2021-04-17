package com;

class Worker {
	private Object lock = new Object();
	
	public void producer() throws InterruptedException {
		
		synchronized (lock) {
			
			System.out.println("On producer 1");
			
			lock.wait(); //stop to wait until lock call notify
			//lock.wait(10000); //wait for 10 second
			
			System.out.println("On producer again 4...");
		}
		
	}
	
	
	
	public void consumer() throws InterruptedException {
		
		Thread.sleep(1000);
		
		synchronized (lock) {
			System.out.println("On consumer 2..");
			
			lock.notify(); //notify threads which wait for the lock can be run  
			
			Thread.sleep(2000); //sleep 2 seconds, it look like a process take 2 seconds
			System.out.println("On consumer again 3...");
		}
	}
	
}


public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		Worker worker = new Worker();

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
		t2.start();
		
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
		t1.start();


		

	}

}
