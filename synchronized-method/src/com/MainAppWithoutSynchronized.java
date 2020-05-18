package com;

public class MainAppWithoutSynchronized {
	
	static int count = 0;
	public static void main(String[] args) {
		
		//create thread 1 to execute function increment
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				increment();
			}
		});
		
		
		//create thread 1 to execute function increment
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				increment();
			}
		});
		
		
		//start each thread
		t1.start();
		t2.start();
		
		
		//wait until thread 1 and 2 finished
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		
		//print out count
		System.out.println("count: " + count);
		
	}
	
	
	//no synchronized
	public static void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
}
