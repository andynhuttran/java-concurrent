package com;

public class MainAppSynchronizedMethod {

	static int count1 = 0;
	static int count2 = 0;
	
	
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {			
			@Override
			public void run() {
				add1();				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {			
			@Override
			public void run() {
				add2();				
			}
		});
		
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
	
	public synchronized static void add1() {
		System.out.println("************start 1***********");
		for (int i = 0; i < 100; i++) {
			System.out.println("On add1");
			count1++;
		}
		System.out.println("************end 1***********");
	}
	
	public synchronized static void add2() {
		System.out.println("************start 2***********");
		for (int i = 0; i < 100; i++) {
			System.out.println("On add2");
			count2++;
		}
		System.out.println("************end 2***********");
	}

}
