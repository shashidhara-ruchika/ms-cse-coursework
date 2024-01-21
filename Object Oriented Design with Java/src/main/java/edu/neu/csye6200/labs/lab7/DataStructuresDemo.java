package edu.neu.csye6200.labs.lab7;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.neu.csye6200.labs.lab7.datastructures.GenericQueue;
import edu.neu.csye6200.labs.lab7.datastructures.GenericStack;
import edu.neu.csye6200.labs.lab7.datastructures.Queue;
import edu.neu.csye6200.labs.lab7.datastructures.Stack;
import edu.neu.csye6200.labs.lab7.errors.EmptyException;
import edu.neu.csye6200.labs.lab7.errors.FullException;

public class DataStructuresDemo {
	
	public static final String ENQUEUE = "ENQUEUE";
	public static final String DEQUEUE = "DEQUEUE";
	public static final String PUSH = "PUSH";
	public static final String POP = "POP";
	public static final String PEEK = "PEEK";
	
	public static class Operation<T> {
		private String operation;
		private T value;
		
		public Operation(String operation, T value) {
			this.operation = operation;
			this.value = value;
		}
		
		public String getOperation() {
			return operation;
		}
		
		public T getValue() {
			return value;
		}
	}
	
	public static void demo () {
		
		DataStructuresDemo.demoIntQueue();
		DataStructuresDemo.demoIntegerQueue();
		DataStructuresDemo.demoStringQueue();
		DataStructuresDemo.demoIntStack();
		DataStructuresDemo.demoIntegerStack();
		DataStructuresDemo.demoStringStack();
		
	}

	public static void demoIntStack() {
		System.out.println("\nDemo for Stack int datatypes\n");
		
		List<Operation<Integer>> operations = new ArrayList<Operation<Integer>>(
				Arrays.asList(
						new Operation<Integer>(DataStructuresDemo.PUSH, 5),
						new Operation<Integer>(DataStructuresDemo.PUSH, 8),
						new Operation<Integer>(DataStructuresDemo.PUSH, 9),
						new Operation<Integer>(DataStructuresDemo.PUSH, 2),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.PUSH, 7),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.PUSH, 3)
				)
		);
		
		Stack stack = new Stack(3);
		stack.display();
		
		operations.stream().forEach(operation -> {
			String type = operation.getOperation();
			
			switch(type) {
			
				case DataStructuresDemo.PUSH:
					Integer value = operation.getValue();
					System.out.print("Push " + value + " ");
					try {
						stack.push(value);
					} catch (FullException e) {
						System.err.print(e.getMessage());
					}
					break;
				
				case DataStructuresDemo.POP:
					System.out.print("Pop ");
					try {
						int poppedNumber = stack.pop();
					} catch (EmptyException e) {
						System.err.print(e.getMessage());
					}
					break;
					
				case DataStructuresDemo.PEEK:
					System.out.print("Pop ");
					try {
						int peekedNumber = stack.peek();
						System.out.print(" Peeked: " + peekedNumber + " ");
					} catch (EmptyException e) {
						System.err.print(e.getMessage());
					}
					break;
				
				default:
					System.err.println("Invalid operation");
					break;	
					
			}
			
			stack.display();
		});
		
		System.out.println();
		
	}

	public static void demoIntegerQueue() {
		System.out.println("\nDemo for Generic Queue Integer datatypes\n");
		
		GenericQueue<Integer> intergerQueue = new GenericQueue<>(Integer.class, 3);
		intergerQueue.display();
		
		List<Operation<Integer>> operationsIntergerQueue = new ArrayList<Operation<Integer>>(
				Arrays.asList(							
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 10),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 20),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 40),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 50),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 60),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 70),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 80)
				)
		);
		
		operationsIntergerQueue.stream().forEach(operation -> {
			String type = operation.getOperation();
			
			switch (type) {
			
			case DataStructuresDemo.ENQUEUE:
				Integer value = operation.getValue();
				System.out.print("Enqueue " + value + " ");
				try {
					intergerQueue.enqueue(value);
				} catch (FullException e) {
					System.err.println(e.getMessage());
				}		
				break;
				
			case DataStructuresDemo.DEQUEUE:
				System.out.print("Dequeue ");
				try {
					int dequeqedNumber = intergerQueue.dequeue();
				} catch (EmptyException e) {
					System.err.println(e.getMessage());
				}
				break;
				
			case DataStructuresDemo.PEEK:
				System.out.print("Peek ");
				try {
					int peekedNumber = intergerQueue.peek();
					System.out.print(" Peeked: " + peekedNumber + " ");
				} catch (EmptyException e) {
					System.err.println(e.getMessage());
				}
				break;
				
			default:
				System.err.println("Invalid operation");
				break;
			
		}
		
			intergerQueue.display();
		});
		
		System.out.println();
		
	}

	public static void demoStringQueue() {
		
		System.out.println("\nDemo for Generic Queue String datatypes\n");
		
		GenericQueue<String> stringQueue = new GenericQueue<>(String.class, 3);
		stringQueue.display();
		
		List<Operation<String>> operationsStringQueue = new ArrayList<Operation<String>>(
				Arrays.asList(							
						new Operation<String>(DataStructuresDemo.ENQUEUE, "garlic"),
						new Operation<String>(DataStructuresDemo.ENQUEUE, "onion"),
						new Operation<String>(DataStructuresDemo.DEQUEUE, null),
						new Operation<String>(DataStructuresDemo.ENQUEUE, "carrot"),
						new Operation<String>(DataStructuresDemo.ENQUEUE, "beans"),
						new Operation<String>(DataStructuresDemo.ENQUEUE, "cucumber"),
						new Operation<String>(DataStructuresDemo.DEQUEUE, null),
						new Operation<String>(DataStructuresDemo.ENQUEUE, "radish"),
						new Operation<String>(DataStructuresDemo.DEQUEUE, null),
						new Operation<String>(DataStructuresDemo.DEQUEUE, null),
						new Operation<String>(DataStructuresDemo.DEQUEUE, null),
						new Operation<String>(DataStructuresDemo.DEQUEUE, null),
						new Operation<String>(DataStructuresDemo.ENQUEUE, "potato")
				)
		);
		
		operationsStringQueue.stream().forEach(operation -> {
			String type = operation.getOperation();
			
			switch (type) {
			
			case DataStructuresDemo.ENQUEUE:
				String value = operation.getValue();
				System.out.print("Enqueue " + value + " ");
				try {
					stringQueue.enqueue(value);
				} catch (FullException e) {
					System.err.println(e.getMessage());
				}		
				break;
				
			case DataStructuresDemo.DEQUEUE:
				System.out.print("Dequeue ");
				try {
					String dequeqedString = stringQueue.dequeue();
				} catch (EmptyException e) {
					System.err.println(e.getMessage());
				}
				break;
				
			case DataStructuresDemo.PEEK:
				System.out.print("Peek ");
				try {
					String peekedString = stringQueue.peek();
					System.out.print(" Peeked: " + peekedString + " ");
				} catch (EmptyException e) {
					System.err.println(e.getMessage());
				}
				break;
				
			default:
				System.err.println("Invalid operation");
				break;
			
		}
		
			stringQueue.display();
		});
		
		
		System.out.println();
		
	}

	public static void demoStringStack() {
		
		System.out.println("\nDemo for Generic Stack String datatypes\n");
		
		GenericStack<String> stringStack = new GenericStack<String>(String.class, 3);
		stringStack.display();
		
		List<Operation<String>> operations = new ArrayList<Operation<String>>(
				Arrays.asList(
						new Operation<String>(DataStructuresDemo.PUSH, "apple"),
						new Operation<String>(DataStructuresDemo.PUSH, "banana"),
						new Operation<String>(DataStructuresDemo.PUSH, "orange"),
						new Operation<String>(DataStructuresDemo.PUSH, "kiwi"),
						new Operation<String>(DataStructuresDemo.POP, null),
						new Operation<String>(DataStructuresDemo.POP, null),
						new Operation<String>(DataStructuresDemo.PUSH, "berry"),
						new Operation<String>(DataStructuresDemo.POP, null),
						new Operation<String>(DataStructuresDemo.POP, null),
						new Operation<String>(DataStructuresDemo.POP, null),
						new Operation<String>(DataStructuresDemo.PUSH, "cherry")
				)
		);
		
		operations.stream().forEach(operation -> {
			String type = operation.getOperation();
			
			switch(type) {
			
				case DataStructuresDemo.PUSH:
					String value = operation.getValue();
					System.out.print("Push " + value + " ");
					try {
						stringStack.push(value);
					} catch (FullException e) {
						System.err.print(e.getMessage());
					}
					break;
				
				case DataStructuresDemo.POP:
					System.out.print("Pop ");
					try {
						String poppedString = stringStack.pop();
					} catch (EmptyException e) {
						System.err.print(e.getMessage());
					}
					break;
					
				case DataStructuresDemo.PEEK:
					System.out.print("Peek ");
					try {
						String peekedString = stringStack.peek();
						System.out.print(" Peeked: " + peekedString + " ");
					} catch (EmptyException e) {
						System.err.print(e.getMessage());
					}
					break;
				
				default:
					System.err.println("Invalid operation");
					break;	
					
			}
			
			stringStack.display();
		});
	
		
		System.out.println();
		
	}

	public static void demoIntegerStack() {
		System.out.println("\nDemo for Generic Stack Integer datatypes\n");
		
		GenericStack<Integer> integerStack = new GenericStack<Integer>(Integer.class, 3);
		integerStack.display();
		
		List<Operation<Integer>> operations = new ArrayList<Operation<Integer>>(
				Arrays.asList(
						new Operation<Integer>(DataStructuresDemo.PUSH, 5),
						new Operation<Integer>(DataStructuresDemo.PUSH, 8),
						new Operation<Integer>(DataStructuresDemo.PUSH, 9),
						new Operation<Integer>(DataStructuresDemo.PUSH, 2),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.PUSH, 7),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.POP, null),
						new Operation<Integer>(DataStructuresDemo.PUSH, 3)
				)
		);
		
		operations.stream().forEach(operation -> {
			String type = operation.getOperation();
			
			switch(type) {
			
				case DataStructuresDemo.PUSH:
					Integer value = operation.getValue();
					System.out.print("Push " + value + " ");
					try {
						integerStack.push(value);
					} catch (FullException e) {
						System.err.print(e.getMessage());
					}
					break;
				
				case DataStructuresDemo.POP:
					System.out.print("Pop ");
					try {
						int poppedNumber = integerStack.pop();
					} catch (EmptyException e) {
						System.err.print(e.getMessage());
					}
					break;
					
				case DataStructuresDemo.PEEK:
					System.out.print("Peek ");
					try {
						int peekedNumber = integerStack.peek();
						System.out.print(" Peeked: " + peekedNumber + " ");
					} catch (EmptyException e) {
						System.err.print(e.getMessage());
					}
					break;
				
				default:
					System.err.println("Invalid operation");
					break;	
					
			}
			
			integerStack.display();
		});
		
		System.out.println();
	}

	public static void demoIntQueue() {
		System.out.println("\nDemo for Queue int datatypes\n");
		
		List<Operation<Integer>> operations = new ArrayList<Operation<Integer>>(
				Arrays.asList(							
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 10),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 20),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 40),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 50),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 60),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 70),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.DEQUEUE, null),
						new Operation<Integer>(DataStructuresDemo.ENQUEUE, 80)
				)
		);
		
		Queue queue = new Queue(3);
		queue.display();
		
		operations.stream().forEach(operation -> {
			String type = operation.getOperation();
			
			switch (type) {
				
				case DataStructuresDemo.ENQUEUE:
					Integer value = operation.getValue();
					System.out.print("Enqueue " + value + " ");
					try {
						queue.enqueue(value);
					} catch (FullException e) {
						System.err.println(e.getMessage());
					}		
					break;
					
				case DataStructuresDemo.DEQUEUE:
					System.out.print("Dequeue ");
					try {
						int dequeqedNumber = queue.dequeue();
					} catch (EmptyException e) {
						System.err.println(e.getMessage());
					}
					break;
					
				case DataStructuresDemo.PEEK:
					System.out.print("Peek ");
					try {
						int peekedNumber = queue.peek();
						System.out.print(" Peeked: " + peekedNumber + " ");
					} catch (EmptyException e) {
						System.err.println(e.getMessage());
					}
					break;
					
				default:
					System.err.println("Invalid operation");
					break;
				
			}
			
			queue.display();
		});
		
		System.out.println();
		
	}

}

