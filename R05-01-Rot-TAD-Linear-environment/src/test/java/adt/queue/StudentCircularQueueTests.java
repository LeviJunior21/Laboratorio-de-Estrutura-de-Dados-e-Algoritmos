package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StudentCircularQueueTests {

	private CircularQueue<Integer> queue1;
	private CircularQueue<Integer> queue2;
	private CircularQueue<Integer> queue3;

	@Before
	public void setup() throws QueueOverflowException {
		this.queue1 = new CircularQueue<Integer>(3);
		this.queue2 = new CircularQueue<Integer>(0);
		this.queue3 = new CircularQueue<Integer>(1);
		
		this.queue1.enqueue(new Integer(1));
		this.queue1.enqueue(new Integer(2));
		this.queue1.enqueue(new Integer(3));
		
		this.queue3.enqueue(new Integer(1));
	}
	
	@Test
	public void equeueWhenIsFull() throws QueueOverflowException {
		try {
			this.queue1.enqueue(new Integer(4));
		}
		catch(QueueOverflowException e) {
			assertEquals("Fila cheia", e.getMessage());
			assertTrue(this.queue1.isFull());
		}
	}
	
	@Test
	public void dequeueWhenIsEmpty() throws QueueUnderflowException {
		try {
			this.queue1.dequeue();
			this.queue1.dequeue();
			this.queue1.dequeue();
			this.queue1.dequeue();	
		}
		catch(QueueUnderflowException e) {
			assertEquals("Fila vazia", e.getMessage());
			assertTrue(this.queue1.isEmpty());
		}
	}
	
	@Test
	public void dequeueAndGetReturn() throws QueueUnderflowException {
		assertEquals(new Integer(1), this.queue1.dequeue());
		assertEquals(new Integer(2), this.queue1.dequeue());
		assertEquals(new Integer(3), this.queue1.dequeue());
	}
	
	@Test
	public void dequeueAndGetHead() throws QueueUnderflowException {
		assertTrue(this.queue1.isFull());
		assertEquals(new Integer(1), this.queue1.dequeue());
		assertFalse(this.queue1.isEmpty());
		assertFalse(this.queue1.isFull());
		assertEquals(new Integer(2), this.queue1.head());
		assertEquals(new Integer(2), this.queue1.dequeue());
		assertEquals(new Integer(3), this.queue1.head());
		assertEquals(new Integer(3), this.queue1.dequeue());
		assertTrue(this.queue1.isEmpty());
	}
	
	@Test
	public void enqueueInArrayLenghtZero() throws QueueOverflowException { 
		try {
			this.queue2.enqueue(new Integer(9));
		}
		catch(QueueOverflowException e) {
			assertEquals("Fila cheia", e.getMessage());
		}
	}
	
	@Test
	public void dequeueInArrayLenghtZero() throws QueueUnderflowException { 
		try {
			this.queue2.dequeue();
		}
		catch(QueueUnderflowException e) {
			assertEquals("Fila vazia", e.getMessage());
		}
	}
	
	@Test
	public void verifyEmptyAndFullInArrayLengthZero() {
		assertTrue(this.queue2.isEmpty());
		assertTrue(this.queue2.isFull());
	}
	
	@Test
	public void enqueueInArrayLenghtOne() throws QueueOverflowException { 
		try {
			assertTrue(this.queue3.isFull());
			this.queue3.enqueue(new Integer(9));
		}
		catch(QueueOverflowException e) {
			assertEquals("Fila cheia", e.getMessage());
		}
	}
	
	@Test
	public void dequeueInArrayLenghtOne() throws QueueUnderflowException { 
		try {
			assertTrue(this.queue3.isFull());
			this.queue3.dequeue();
			assertTrue(this.queue3.isEmpty());
			this.queue3.dequeue();
		}
		catch(QueueUnderflowException e) {
			assertEquals("Fila vazia", e.getMessage());
		}
	}
	
	@Test
	public void verifyEmptyAndFullInArrayLengthOne() {
		assertFalse(this.queue3.isEmpty());
		assertTrue(this.queue3.isFull());
	}
}
