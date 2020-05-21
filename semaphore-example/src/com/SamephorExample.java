package com;

import java.util.concurrent.Semaphore;

class ATMMachine implements Runnable {

	
	private Semaphore semaphore;
	private String name;
	
	public ATMMachine(Semaphore semaphore, String name){
		this.semaphore = semaphore;
		this.name = name;
	}
	
	
	
	@Override	
	public void run() {
		try {
			withDraw();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	private void withDraw() throws InterruptedException {
		System.out.println(name + " - number or permit: " + semaphore.availablePermits());	
		
		semaphore.acquire(); //require a permit
		System.out.println(name + " - got permit: ");
		
		System.out.println("\t There are " + semaphore.getQueueLength() + " threads waiting to get permit");
		System.out.println("\t\t This message is printed in " + name);
		
		processWithDraw();
		System.out.println(name + " is done withdraw");
		
		semaphore.release(); //release a permit to other threads
		System.out.println(name + " - release a permit: " + semaphore.availablePermits());
	}



	private void processWithDraw() throws InterruptedException {
		SamephorExample.AMOUNT_OF_MONEY -= 100;
		Thread.sleep(1000); //sleep 1 second, simulate process with draw
	}
	
}


public class SamephorExample {

	
	static int AMOUNT_OF_MONEY = 5000;
	final static int NUMBER_OF_PERMIT = 1;
	final static int NUMBER_OF_AMT = 5;
	
	
	public static void main(String[] args) {
		
		//define a semaphore
		Semaphore semaphore = new Semaphore(NUMBER_OF_PERMIT);
		
		Thread[] ts = new Thread[NUMBER_OF_AMT];
		
		//create thread for each ATM
		for (int i = 0; i < NUMBER_OF_AMT; ++i){
			ATMMachine atm = new ATMMachine(semaphore, "ATM_" + (i+1));
			Thread t = new Thread(atm);
			ts[i] = t;
		}
		
		//start each thread
		for (Thread t : ts){
			t.start();
		}
		
		
		//wait until all thread are finished
		for (Thread t : ts){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//print final money
		System.out.println("AMOUNT_OF_MONEY: " + SamephorExample.AMOUNT_OF_MONEY);
		
	}

}
