package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StudentCircularQueueTests {

	public CircularQueue<Integer> queue;
	
	@Before
	public void setup() throws QueueOverflowException {
		this.queue = new CircularQueue<Integer>(3);
		
		this.queue.enqueue(new Integer(1));
		this.queue.enqueue(new Integer(2));
		this.queue.enqueue(new Integer(3));
	}
	
	@Test(expected = QueueOverflowException.class)
	public void equeueWhenIsFull() throws QueueOverflowException {
		try {
			this.queue.enqueue(new Integer(4));
		}
		catch(QueueOverflowException e) {
			assertEquals("Fila cheia", e.getMessage());
			assertTrue(this.queue.isFull());
		}
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void dequeueWhenIsEmpty() throws QueueUnderflowException {
		try {
			this.queue.dequeue();
			this.queue.dequeue();
			this.queue.dequeue();
			this.queue.dequeue();	
		}
		catch(QueueUnderflowException e) {
			assertEquals("Fila vazia", e.getMessage());
			assertTrue(this.queue.isEmpty());
		}
	}
	
	@Test
	public void dequeueAndGetReturn() throws QueueUnderflowException {
		assertEquals(new Integer(1), this.queue.dequeue());
		assertEquals(new Integer(2), this.queue.dequeue());
		assertEquals(new Integer(3), this.queue.dequeue());
	}
	
	@Test
	public void dequeueAndGetHead() throws QueueUnderflowException {
		assertEquals(new Integer(1), this.queue.dequeue());
		assertEquals(new Integer(2), this.queue.head());
		assertEquals(new Integer(2), this.queue.dequeue());
		assertEquals(new Integer(3), this.queue.head());
	}
	
}
