package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	
	public RecursiveDoubleLinkedListImpl(
			RecursiveDoubleLinkedListImpl<T> previous,
			RecursiveDoubleLinkedListImpl<T> next) 
	{
		this.previous = previous;
		this.next = next;
	}

	public RecursiveDoubleLinkedListImpl(RecursiveDoubleLinkedListImpl<T> previous, 
			RecursiveDoubleLinkedListImpl<T> next,
			T data) 
	{
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {

			if (this.isEmpty()) {
				this.insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>(this, 
																((RecursiveDoubleLinkedListImpl<T>) this.next),
																data);

				data = element;
				next = newNode;

				((RecursiveDoubleLinkedListImpl<T>) newNode.getNext()).setPrevious(newNode);
				
			}
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (data == null){
				data = element;
				
				RecursiveDoubleLinkedListImpl<T> newNull = new RecursiveDoubleLinkedListImpl<T>(this, null);
				this.next = newNull;

				if (previous == null) {
					previous = new RecursiveDoubleLinkedListImpl<T>(null, this);
				}
			}

			else {
				next.insert(element);
			}
		}
	}
	
	@Override
	public void removeFirst() {
		if (!super.isEmpty()) {
			
			if (previous.isEmpty() && next.isEmpty()) {
				data = null;
				next = null;
				previous = null;
			}

			else {
				data = next.getData();
				next = next.getNext();

				((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
			}  
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getPrevious().isEmpty() && super.getNext().isEmpty()) {
				data = null;
				next = null;
				previous = null;
			} else if (super.getNext().isEmpty()) {
				data = ((RecursiveDoubleLinkedListImpl<T>) this.previous).data;
				previous = this.previous.getPrevious();

				this.getPrevious().setNext(this);
			}

			else {
				((RecursiveDoubleLinkedListImpl<T>) super.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
