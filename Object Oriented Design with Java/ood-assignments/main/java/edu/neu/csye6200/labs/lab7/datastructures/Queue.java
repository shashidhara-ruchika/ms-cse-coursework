package edu.neu.csye6200.labs.lab7.datastructures;

import edu.neu.csye6200.labs.lab7.errors.EmptyException;
import edu.neu.csye6200.labs.lab7.errors.FullException;

public class Queue {
	
	private int[] arr;
	private int capacity;
	private int front;
	private int rear;
	private int count;
	
	public Queue(int size) {
		arr = new int[size];
		capacity = size;
		front = 0;
		rear = -1;
		count = 0;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public boolean isFull() {
		return (size() == capacity);
	}
	
	public void enqueue(int item) throws FullException {
		if (isFull()) {
			throw new FullException();
		}
		System.out.print("Adding " + item);
		rear = (rear + 1) % capacity; // circular index
		arr[rear] = item;
		count++;
	}
	
	public int dequeue() throws EmptyException {
		if (isEmpty()) {
			throw new EmptyException();
		}
		int e = arr[front];
		System.out.print("Removing " + e);
		front = (front + 1) % capacity; // circular index
		count--;
		return e;
	}
	
	public int peek() throws EmptyException {
		if (isEmpty()) {
			throw new EmptyException();
		}
		return arr[front];
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" Current Queue: ");
		int i = front;
        for (int j = 0; j < count; j++) {
            stringBuilder.append(arr[i]).append(" ");
            i = (i + 1) % capacity; // Circular index
        }
		stringBuilder.append(" Size: ");
		stringBuilder.append(count);
		stringBuilder.append(" Capacity: ");
		stringBuilder.append(capacity);
		return stringBuilder.toString();
	}
	
	public void display() {
		System.out.println(this.toString());
	}
	
}
