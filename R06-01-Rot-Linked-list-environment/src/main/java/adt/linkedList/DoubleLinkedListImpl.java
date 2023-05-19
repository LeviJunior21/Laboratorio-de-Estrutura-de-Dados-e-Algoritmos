package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			newNode.setData(element);
			newNode.setNext(head);
			this.head = newNode;
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if(this.head.next.isNIL()) {
				head = new DoubleLinkedListNode<T>();
				last = (DoubleLinkedListNode<T>) head;
			} else {
				this.head = this.head.getNext();
				((DoubleLinkedListNode<T>) this.head).setPrevious(null);	
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if(this.last.previous.isNIL()) {
				head = new DoubleLinkedListNode<T>();
				last = (DoubleLinkedListNode<T>) head;
			} else {
				this.last = this.last.getPrevious();
				this.last.setNext(new DoubleLinkedListNode<T>());
			}
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNextNull = new DoubleLinkedListNode<>();		
			DoubleLinkedListNode<T> newDoubleNode = new DoubleLinkedListNode<>();
			
			newNextNull.setPrevious(newDoubleNode);
			newDoubleNode.setNext(newNextNull);
			
			newDoubleNode.setData(element);
			
			if (this.isEmpty()) {
				head = newDoubleNode;
				last = newDoubleNode;
				
				DoubleLinkedListNode<T> newPrevNull = new DoubleLinkedListNode<>();
				newPrevNull.setNext(head);
				
				((DoubleLinkedListNode<T>) head).setPrevious(newPrevNull);
				
			} else {
				newDoubleNode.setPrevious(last);
				last.setNext(newDoubleNode);

				last = newDoubleNode;
			}
		}
	}
	
	@Override
	public void remove(T element) {
		if (!this.isEmpty()) {
			if (this.last.getData().equals(element)) {
				this.removeLast();
			} else if (this.head.getData().equals(element)) {
				this.removeFirst();
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
				while ((!aux.isNIL())) {
					if (aux.getData().equals(element)) {
						aux.getPrevious().setNext(aux.getNext());
						((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
						break;
					} else {
						aux = (DoubleLinkedListNode<T>) aux.getNext();
					}
				}			
			}
		}				
	}
	
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
