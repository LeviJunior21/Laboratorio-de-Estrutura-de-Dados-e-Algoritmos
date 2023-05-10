package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		if (this.isEmpty()) {
			this.head = 0;
		}
		this.tail += 1;
		this.elements += 1;
		this.array[this.tail % this.array.length] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		this.elements -= 1;
		T element = this.array[this.head % this.array.length];
		this.head += 1;
		
		if (head > tail) {
			this.head = -1;
			this.tail = -1;
		}
		
		return element;
	}

	@Override
	public T head() {
		return (this.array[this.head % this.array.length]);
	}

	@Override
	public boolean isEmpty() {
		return (this.head == -1 && this.tail == -1);
	}

	@Override
	public boolean isFull() {
		return this.tail - this.head + 1 == this.array.length;
	}

}
