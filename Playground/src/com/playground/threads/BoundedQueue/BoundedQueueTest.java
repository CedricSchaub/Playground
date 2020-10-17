package com.playground.threads.BoundedQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoundedQueueTest {

	BoundedQueue<Integer> queue;
	final static int QUEUE_CAP = 1000;
	
	@BeforeAll
	static void init() {
		
	}
	
	@AfterAll
	static void close() {
		
	}
	
	@BeforeEach
	void init_before_each_test() {
		queue = new BoundedQueue<>(QUEUE_CAP);
	}
	
	@Test
	@DisplayName("Check element capacity")
	public void check_count() {
		
		assertEquals(0, queue.count());
	
		queue.enqueue(2);
		
		assertEquals(1, queue.count());
		
		queue.enqueue(2);
		
		assertEquals(2, queue.count());
		
		queue.dequeue();
		queue.dequeue();
		
		assertEquals(0, queue.count());
	}
	
	@Test
	@DisplayName("Check element size")
	public void check_size() {
		
		int expected = QUEUE_CAP;
		assertEquals(expected, queue.size());
	}
	
	@Test
	public void add_one_remove_one() {
		int expected = 5;
		queue.enqueue(expected);
		
		int result = queue.dequeue();
		
		assertEquals(expected, result);
	}
	
	@Test
	public void add_all_remove_all() {
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		
		assertAll("Add All - Remove All",
					() -> assertEquals(1, queue.dequeue()),
					() -> assertEquals(2, queue.dequeue()),
					() -> assertEquals(3, queue.dequeue())
				);
		
	}
	
	@Test
	public void add_elements_threaded() {
		
		var doWork = new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread started.");
				for(int i = 0; i < 500; i++) {
					queue.enqueue(i);
				}
			}
		};
		
		Thread[] worker = new Thread[] {new Thread(doWork), new Thread(doWork)};
		
		
		worker[0].start();
		worker[1].start();
		
		// Busy waiting to get the right result from queue.count()
		while(worker[0].isAlive() || worker[1].isAlive()) {}
		
		assertEquals(QUEUE_CAP, queue.count());
		
	}
	
	@Test
	public void add_more_elements_than_cap_threaded() {
		var enqueueElementsUntilCap = new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread started.");
				for(int i = 0; i < QUEUE_CAP + 10; i++) {
					queue.enqueue(i);
				}
			}
		};
		
		var dequeueAllElements = new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread started.");
				for(int i = 0; i < QUEUE_CAP; i++) {
					queue.dequeue();
				}
			}
		};
		
		Thread enqueueWorker = new Thread(enqueueElementsUntilCap);
		enqueueWorker.start();
		
		Thread dequeueWorker = new Thread(dequeueAllElements);
		dequeueWorker.start();
		
		// Busy waiting to geht the right result from queue.count()
		while(enqueueWorker.isAlive() || dequeueWorker.isAlive()) {}
		
		assertEquals(10, queue.count());
		
	}
}
