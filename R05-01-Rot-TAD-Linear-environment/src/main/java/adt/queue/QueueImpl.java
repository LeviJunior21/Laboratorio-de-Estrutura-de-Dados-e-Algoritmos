package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (tail == -1) {
			return null;
		}
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return tail == -1; 
	}

	@Override
	public boolean isFull() {
		return tail == this.array.length - 1;
	}

	private void shiftLeft() {
		this.tail -= 1;
		for (int index = 0; index <= tail; index++) {
			this.array[index] = array[index + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		this.tail += 1;
		this.array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T element = this.array[0];
		this.shiftLeft();
		return element;
	}

}
