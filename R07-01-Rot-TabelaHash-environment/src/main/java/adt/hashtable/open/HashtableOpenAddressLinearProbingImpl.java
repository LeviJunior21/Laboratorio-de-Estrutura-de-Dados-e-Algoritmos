package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int prob = 0;
			while(prob < this.table.length) {
				int hash = ((HashFunctionOpenAddress<T>)hashFunction).hash(element, prob);
				if (table[hash] == null || table[hash].equals(deletedElement)) {
					this.table[hash] = element;
					break;
				}

				prob++;
			}

			if(prob == this.table.length) 
				throw new HashtableOverflowException();

			COLLISIONS += prob;
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		int index = indexOf(element);
		if (index != -1) {
			this.table[index] = deletedElement;
			elements--;		
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		
		int index = indexOf(element);
		if (index != -1)
			result = (T) this.table[index];
		
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		
		int prob = 0;
		while(prob < this.table.length) {
			int hash = ((HashFunctionOpenAddress<T>)hashFunction).hash(element, prob);
			if (table[hash] != null) {
				if (this.table[hash].equals(element)) {
					result = hash;
					break;
				}
			} else {
				break;
			}
			
			prob++;
		}
		
		return result;
	}

}
