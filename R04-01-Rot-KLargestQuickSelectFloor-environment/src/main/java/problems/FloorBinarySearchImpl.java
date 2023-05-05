package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array != null && x != null && array.length != 0) {
			quickSort(array, 0, array.length - 1);
			return binarySearch(array, 0, array.length - 1, x , null);
		}
		else {
			return null;
		}
	}
	
	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && leftIndex < rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivot - 1);
			quickSort(array, pivot + 1, rightIndex);
		}
	}
	
	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		int pivot = leftIndex;
		int predecessor = leftIndex;
		
		for (int index = leftIndex + 1; index <= rightIndex; index++) {
			if (array[index].compareTo(array[pivot]) == -1) {
				predecessor++;
				Util.swap(array, index, predecessor);
			}
		}
		
		Util.swap(array, pivot, predecessor);
		return predecessor;
	}
	
	private Integer binarySearch(Integer[] array, int leftIndex, int rightIndex, Integer x, Integer floor) {
		if (leftIndex <= rightIndex) {
			int mid = (leftIndex + rightIndex) / 2;
			if (x.compareTo(array[mid]) == 0) {
				return array[mid];
			}
			if (floor == null && array[mid] < x || array[mid] < x && array[mid] > floor) {
				floor = array[mid];
			}
			if (x > array[mid]) {
				return binarySearch(array, mid + 1, rightIndex, x, floor);
			}
			else {
				return binarySearch(array, leftIndex, mid - 1, x, floor);
			}
		}
		else {
			return floor;
		}
	}
}
