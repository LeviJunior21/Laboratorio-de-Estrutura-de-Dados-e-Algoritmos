package adt.queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueUsingStackTests {

	private QueueUsingStack<Integer> queue;
	
	@Before
	public void setup() throws QueueOverflowException {
		this.queue = new QueueUsingStack<Integer>(3);
		
		this.queue.enqueue(new Integer(1));
		this.queue.enqueue(new Integer(2));
		this.queue.enqueue(new Integer(3));
	}
	
	@Test
	public void whenEnqueueInArrayFull() throws QueueOverflowException {
		try {
			assertTrue(this.queue.isFull());
			this.queue.enqueue(new Integer(4));
		}
		catch (QueueOverflowException e) {
			assertEquals("Fila cheia", e.getMessage());
		}
	}
	
	@Test
	public void whenDequeueInArrayEmpty() throws QueueUnderflowException {
		try {
			assertTrue(this.queue.isFull());
			assertEquals(new Integer(1), this.queue.head());
			assertEquals(new Integer(1) ,this.queue.dequeue());
			assertEquals(new Integer(2), this.queue.head());
			assertEquals(new Integer(2) ,this.queue.dequeue());
			assertFalse(this.queue.isEmpty());
			assertFalse(this.queue.isFull());
			assertEquals(new Integer(3), this.queue.head());
			assertEquals(new Integer(3) ,this.queue.dequeue());
			assertTrue(this.queue.isEmpty());
			this.queue.dequeue();
		}
		catch (QueueUnderflowException e) {
			assertEquals("Fila vazia", e.getMessage());
		}
	}
}
