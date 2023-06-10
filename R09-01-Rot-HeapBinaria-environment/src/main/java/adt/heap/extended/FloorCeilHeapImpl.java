package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = null;
		
		if (array != null) {
			Comparator<Integer> oldComparator = this.comparator;
			this.comparator = ((o1, o2) -> o1.compareTo(o2));

			this.heap = new Integer[array.length];
			this.index = -1;
			
			for (Integer num : array) {
				if (num <= numero) {
					insert(num);
				}
			}

			this.comparator = oldComparator;

			result = this.rootElement();
		}
		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = null;
		
		if (array != null) {
			Comparator<Integer> oldComparator = this.comparator;
			this.comparator = ((o1, o2) -> o2.compareTo(o1));

			this.heap = new Integer[array.length];
			this.index = -1;
			
			for (Integer num : array) {
				if (num >= numero) {
					insert(num);
				}
			}
		
			this.comparator = oldComparator;
			result = this.rootElement();
		}
		
		return result;
	}

}