package com.playground.threads;

public class LoopThread extends Thread {

	private final String name;

	public LoopThread(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		int x = 0;
		System.out.println(name + " started");
		for (int i = 0; i < 1000000; i++) {
			for (int j = 0; j < 1000000; j++) {
				x = i * j;
			}
		}
		System.out.println(name + " finished");
	}

}
