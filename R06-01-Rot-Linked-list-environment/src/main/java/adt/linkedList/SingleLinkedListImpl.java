package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			size += 1;
			aux = aux.getNext();
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			if (aux.getData() == element) {
				result = aux.getData();
				break;
			}
			
			aux = aux.getNext();
		}
		
		return result;
	}

	@Override
	public void insert(T element) {
		
		if (element != null) {

			SingleLinkedListNode<T> aux = head;
			while (!aux.isNIL()) {				
				aux = aux.getNext();
			}

			aux.setData(element);
			aux.next = new SingleLinkedListNode<>();
		
		}
	}
	
	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> aux = head;
		
		while (!aux.isNIL()) {				
			if (aux.getData().equals(element)) {
				aux.setData(aux.getNext().getData());
				aux.setNext(aux.getNext().getNext());
				break;
			}
			aux = aux.getNext();
		}
		
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		
		int lastIndex = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			array[lastIndex++] = aux.getData();
			aux = aux.getNext();
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
