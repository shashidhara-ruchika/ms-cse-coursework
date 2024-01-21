package edu.neu.csye6200.labs.lab7.datastructures;

import java.lang.reflect.Array;

import edu.neu.csye6200.labs.lab7.errors.EmptyException;
import edu.neu.csye6200.labs.lab7.errors.FullException;

public class GenericQueue<T> {
	
	private T[] arr;
	private int capacity;
	private int front;
	private int rear;
	private int count;
	
	public GenericQueue(Class<T> genericClass, int size) {
		arr = (T[]) Array.newInstance(genericClass, size);
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
	
	public void enqueue(T item) throws FullException {
		if (isFull()) {
			throw new FullException();
		}
		System.out.print("Adding " + item);
		rear = (rear + 1) % capacity; // circular index
		arr[rear] = item;
		count++;
	}
	
	public T dequeue() throws EmptyException {
		if (isEmpty()) {
			throw new EmptyException();
		}
		T e = arr[front];
		System.out.print("Removing " + e);
		front = (front + 1) % capacity; // circular index
		count--;
		return e;
	}
	
	public T peek() throws EmptyException {
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

