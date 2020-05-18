package com;


public class MainAppSynchronizedBlock {
	
	static int count1 = 0;
	static int count2 = 0;
	
	public static void main(String[] args) {
		
		//create thread 1
		Thread t1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				add1();				
			}
		});
		
		//thread 2
		Thread t2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				add2();				
			}
		});
		
		
		//start them
		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("count1: " + count1);
		System.out.println("count2: " + count2);
		
	}
	
	static Object lock1 = new Object();
	static Object lock2 = new Object();
	
	public static void add1() {
		System.out.println("************start 1***********");
		synchronized (lock1) {
			for (int i = 0; i < 100; i++) {
				System.out.println("On add1");
				count1++;
			}
		}
		System.out.println("************end 1***********");
	}
	
	public static void add2() {
		System.out.println("************start 2***********");
		synchronized (lock2) {
			for (int i = 0; i < 100; i++) {
				System.out.println("On add2");
				count2++;
			}
		}
		System.out.println("************end 2***********");
	}
	
	
}
