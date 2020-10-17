package com.playground.threads.BoundedQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {

	private List<T> queueElements;
	private final int QUEUE_CAP;

	ReentrantLock lock = new ReentrantLock();
	Condition queueAtLeastOneElementCondition = lock.newCondition();
	Condition queueFullCondition = lock.newCondition();

	public BoundedQueue(int size) {
		QUEUE_CAP = size;
		queueElements = new LinkedList<>();
	}

	/*public void enqueue(T element) {
		try {
			lock.lock();
			while(queueElements.size() == QUEUE_CAP) {
				System.out.println("ENQUEUE - Wait until queue can add another element.");
				queueFullCondition.await();
				System.out.println("ENQUEUE - Queue can add another element.");
			}
			
			queueElements.add(element);
			queueAtLeastOneElementCondition.signalAll();
			
		} catch(InterruptedException e) {
			System.out.println("ENQUEUE - Thread has been interrputed.");
		} finally {
			lock.unlock();
		}
	} */

	/*public T dequeue() {
		try {
			lock.lock();
			while(queueElements.size() == 0) {
				System.out.println("DEQUEUE - Wait until queue has atleast one element.");
				queueAtLeastOneElementCondition.await();
				System.out.println("DEQUEUE - Queue has atleast one element.");
			}
			
			T element = queueElements.remove(0);
			queueFullCondition.signalAll();
			
			return element;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("DEQUEUE - Thread has been interrputed.");
		} finally {
			lock.unlock();
		}
		
		return null;
	}*/
	
	public void enqueue(T element) {
		queueElements.add(element);
	}
	
	public T dequeue() {
		return (queueElements.size() > 0) ? queueElements.get(0) : null;
		
	}

	int count() {
		return queueElements.size();
	}

	int size() {
		return QUEUE_CAP;
	}
}
