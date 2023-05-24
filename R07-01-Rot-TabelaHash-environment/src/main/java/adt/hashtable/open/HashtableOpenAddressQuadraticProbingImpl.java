package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int prob = 0;
			while(prob < this.table.length) {
				int hash = ((HashFunctionOpenAddress<T>)hashFunction).hash(element, prob);
				if (table[hash] == null || table[hash].equals(deletedElement) || table[hash].equals(element)) {
					this.table[hash] = element;
					this.elements++;
					break;
				}

				prob++;
				COLLISIONS++;
			}

			if (prob == this.table.length) 
				throw new HashtableOverflowException();

		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int index = indexOf(element);
			if (index != -1) {
				this.table[index] = deletedElement;
				elements--;
			}
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
		if (element != null) {
			int prob = 0;
			while(prob < this.table.length && !isEmpty()) {
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
		}
		
		return result;
	}
}
