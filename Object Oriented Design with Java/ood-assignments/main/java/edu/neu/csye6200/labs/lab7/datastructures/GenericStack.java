package edu.neu.csye6200.labs.lab7.datastructures;

import java.lang.reflect.Array;

import edu.neu.csye6200.labs.lab7.errors.EmptyException;
import edu.neu.csye6200.labs.lab7.errors.FullException;

public class GenericStack<T> {
	
	private T[] arr;
	private int capacity;
	private int top;
	
	public GenericStack(Class<T> genericClass, int size) {
		arr = (T[]) Array.newInstance(genericClass, size);
		capacity = size;
		top = -1;
	}
	
	public int size() {
		return top + 1;
	}
	
	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
		return (top == capacity - 1);
	}
	
	public void push(T item) throws FullException {
		if (isFull()) {
			throw new FullException();
		}
		System.out.print("Inserting " + item);
		arr[++top] = item;
	}
	
	public T pop() throws EmptyException {
		if (isEmpty()) {
			throw new EmptyException();
		}
		System.out.print("Removing " + peek());
		return arr[top--];
	}
	
	public T peek() throws EmptyException {
		if (isEmpty()) {
			throw new EmptyException();				
		} 
		return arr[top];
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" Current Queue: ");
		for (int i = top; i >= 0; i--) {
			stringBuilder.append(arr[i]).append(" ");
		}
		stringBuilder.append(" Size: ");
		stringBuilder.append(size());
		stringBuilder.append(" Capacity: ");
		stringBuilder.append(capacity);
		return stringBuilder.toString();
	}
	
	public void display() {
		System.out.println(this.toString());
	}
	
	
}


