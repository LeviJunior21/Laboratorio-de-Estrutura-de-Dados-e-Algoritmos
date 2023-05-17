package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int size = 0;
		
		if (this.data != null)
			size += 1 + next.size();
		
		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		
		if (this.data != null) {
			if (this.data == element) {
				result = this.data;
			} else {
				result = next.search(element);
			}
		}
			
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.data == null) {
				this.next = new RecursiveSingleLinkedListImpl<T>();
				this.data = element;
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.data == element) {
			this.data = this.next.getData();
			this.next = this.next.getNext();
		}
		
		else if (this.data != null) {
			this.next.remove(element);
		}
		
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[0];
		
		if (this.data != null) {
			T[] nextArray = next.toArray();
			
			array = (T[]) new Object[nextArray.length + 1];
			
			array[0] = this.data;
			for (int i = 1; i < array.length; i++) {
				array[i] = nextArray[i - 1];
			}
		}
		
		return array;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
