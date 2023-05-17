package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueCircular2Tests {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;
	public Queue<Integer> queue4;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		queue4.enqueue(50);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		/**
		queue1 = new QueueUsingStack<>(4);
		queue2 = new QueueUsingStack<>(2);
		queue3 = new QueueUsingStack<>(8);
		queue4 = new QueueUsingStack<>(8);

		queue1 = new QueueUsingStack<>(4);
		queue2 = new QueueUsingStack<>(2);
		queue3 = new QueueUsingStack<>(8);
		queue4 = new QueueUsingStack<>(8);
		**/
		
		queue1 = new CircularQueue<>(4);
		queue2 = new CircularQueue<>(2);
		queue3 = new CircularQueue<>(8);
		queue4 = new CircularQueue<>(8);
		
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() {
		assertEquals(new Integer(1), queue1.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDequeueUmElemento() {
		try {
			assertEquals(new Integer(50), queue4.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		assertEquals(new Integer(1), queue3.dequeue()); // vai depender do												// tamanho que a fial
														// foi iniciada!!!
	}
	
	@Test
	public void testDequeEnque() {
		try {
			assertEquals(new Integer(1), queue2.dequeue());
			queue2.enqueue(new Integer(50));
		} catch (Exception e) {
			// TODO: handle exception
			fail("erro inesperado");
		}
	}
	
	@Test
	public void testDequeAll() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
			assertEquals(new Integer(2), queue1.dequeue());
			assertEquals(new Integer(3), queue1.dequeue());
			assertTrue(queue1.isEmpty());
		} catch (Exception e) {
			fail("erro inesperado");
		}
	}
	@Test
	public void testDequeEnqueAll() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
			assertEquals(new Integer(2), queue1.dequeue());
			assertEquals(new Integer(3), queue1.dequeue());
			
			assertTrue(queue1.isEmpty());
			assertFalse(queue1.isFull());
			
			queue1.enqueue(new Integer(5));
			queue1.enqueue(new Integer(7));
			queue1.enqueue(new Integer(8));
			queue1.enqueue(new Integer(90));
			
			assertFalse(queue1.isEmpty());
			assertTrue(queue1.isFull());
			
			assertEquals(new Integer(5), queue1.dequeue());
			assertEquals(new Integer(7), queue1.dequeue());
			assertEquals(new Integer(8), queue1.dequeue());
			assertEquals(new Integer(90), queue1.dequeue());
			
			assertTrue(queue1.isEmpty());
			assertFalse(queue1.isFull());
			
			queue1.enqueue(new Integer(50));
			
			assertFalse(queue1.isEmpty());
			assertFalse(queue1.isFull());
		} catch (Exception e) {
			fail("erro inesperado");
		}
	}
	
	@Test
	public void testIsFullAll() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
			assertEquals(new Integer(2), queue1.dequeue());
			assertEquals(new Integer(3), queue1.dequeue());
			
			queue1.enqueue(new Integer(5));
			queue1.enqueue(new Integer(7));
			queue1.enqueue(new Integer(8));
			queue1.enqueue(new Integer(90));
			
			assertEquals(new Integer(5), queue1.dequeue());
			assertEquals(new Integer(7), queue1.dequeue());
			assertEquals(new Integer(8), queue1.dequeue());
			assertEquals(new Integer(90), queue1.dequeue());
			
			queue1.enqueue(new Integer(5));
			queue1.enqueue(new Integer(7));
			queue1.enqueue(new Integer(8));
			queue1.enqueue(new Integer(90));
			
			assertTrue(queue1.isFull());
	
		} catch (Exception e) {
			fail("erro inesperado");
		}
	}
	
	@Test
	public void testIsEmptyAll() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
			assertEquals(new Integer(2), queue1.dequeue());
			assertEquals(new Integer(3), queue1.dequeue());
			
			queue1.enqueue(new Integer(5));
			queue1.enqueue(new Integer(7));
			queue1.enqueue(new Integer(8));
			queue1.enqueue(new Integer(90));
			
			assertEquals(new Integer(5), queue1.dequeue());
			assertEquals(new Integer(7), queue1.dequeue());
			assertEquals(new Integer(8), queue1.dequeue());
			assertEquals(new Integer(90), queue1.dequeue());
			
			queue1.enqueue(new Integer(5));
			queue1.enqueue(new Integer(7));
			queue1.enqueue(new Integer(8));
			queue1.enqueue(new Integer(90));
			
			assertTrue(queue1.isFull());
			
		} catch (Exception e) {
			fail("erro inesperado");
		}
	}
}