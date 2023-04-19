package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && rightIndex < array.length) {
			int meio = (leftIndex + rightIndex) / 2;
			if (leftIndex < rightIndex) {
				sort(array, leftIndex , meio);
				sort(array, meio + 1, rightIndex);
				merge(array, meio, leftIndex, rightIndex);
			}
		}
	}
	
	private void merge(T[] array, int meio,  int inicio, int fim) {
		T[] arrayHelper = helperCopyOf(array);
		int index = inicio;
		
		int esquerda = inicio;
		int direita = meio + 1;
		
		while(esquerda <= meio && direita <= fim) {
			if (arrayHelper[esquerda].compareTo(arrayHelper[direita]) == -1) {
				array[index] = arrayHelper[esquerda];
				esquerda ++;
			}
			else if (arrayHelper[direita].compareTo(arrayHelper[esquerda]) == -1) {
				array[index] = arrayHelper[direita];
				direita ++;
			}
			else {
				array[index] = arrayHelper[esquerda];
				esquerda ++;
			}
			index++;
		}
		
		while (esquerda <= meio) {
			array[index] = arrayHelper[esquerda];
			esquerda++;
			index++;
		}
		
		while (direita <= fim) {
			array[index] = arrayHelper[direita];
			direita++;
			index++;
		}
		
	}
	
	private T[] helperCopyOf(T[] array) {
		T[] arrayHelper = (T[]) new Comparable[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayHelper[i] = array[i];
		}
		return arrayHelper;
	}
	
}
