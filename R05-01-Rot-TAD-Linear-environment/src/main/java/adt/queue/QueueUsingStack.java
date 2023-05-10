package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if (this.stack1.isFull()) {
			throw new QueueOverflowException();
		}
		try {
			this.stack1.push(element);
		}
		catch(StackOverflowException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		T element = null;
		
		if (this.stack1.isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		try {
			this.dequeueStack1ToStack2();
			element = this.stack2.pop();
			this.dequeueStack2ToStack1();
			
		} catch (StackUnderflowException e) {}
		
		return element;
	}

	private void dequeueStack1ToStack2() {
		while (!this.stack1.isEmpty()) {
			try {
				this.stack2.push(this.stack1.pop());
			}
			catch(StackOverflowException | StackUnderflowException e) {}
		}
	}
	
	private void dequeueStack2ToStack1() {
		while (!this.stack2.isEmpty()) {
			try {
				this.stack1.push(this.stack2.pop());
			}
			catch(StackOverflowException | StackUnderflowException e) {}
		}
	}
	
	@Override
	public T head() {
		T element = null;
		try {
			if (!this.isEmpty()) {
				this.dequeueStack1ToStack2();
				element = this.stack2.top();
				this.dequeueStack2ToStack1();
			}
		}
		catch(Exception e) {}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
