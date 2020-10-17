package com.playground.threads;

public class SleepThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("SleepThread goes to sleep!");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("SleepThread was interrupted");
		}
	}

}
