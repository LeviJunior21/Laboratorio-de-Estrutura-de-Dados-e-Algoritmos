package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		mergeSortHybrid(array, leftIndex, rightIndex);
	}
	
	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int index = leftIndex + 1; index <= rightIndex; index++) {
			int aux = index;
			while (aux > leftIndex && array[aux].compareTo(array[aux - 1]) == -1) {
				Util.swap(array, aux, aux - 1);
				aux--;
			}
		}
		INSERTIONSORT_APPLICATIONS += 1;
	}
	
	private void mergeSortHybrid(T[] array, int leftIndex, int rightIndex) {
		if (SIZE_LIMIT >= 0 && leftIndex >= 0 && rightIndex >= 0) { 
			if (leftIndex <= rightIndex) {
				if ((rightIndex - leftIndex + 1) <= SIZE_LIMIT) {
					insertionSort(array, leftIndex, rightIndex);
				}
				else {
					int medium = (leftIndex + rightIndex) / 2;
					mergeSortHybrid(array, leftIndex, medium);	
					mergeSortHybrid(array, medium + 1, rightIndex);		
					merge(array, leftIndex, medium, rightIndex);
				}
			}
		}
	}
	
	private void merge(T[] array, int leftIndex, int medium, int rightIndex) {
		T[] helper = helperAux(array);
		int index = leftIndex;
		int left = leftIndex;
		int right = medium + 1;
		
		while (left <= medium && right <= rightIndex) {
			if (helper[left].compareTo(helper[right]) == -1) {
				array[index] = helper[left];
				left++;
			}
			else {
				array[index] = helper[right];
				right++;
			}
			index++;
		}
		while (left <= medium) {
			array[index] = helper[left];
			index++;
			left++;
		}
		while (right <= rightIndex) {
			array[index] = helper[right];
			index++;
			right++;
		}
		MERGESORT_APPLICATIONS += 1;
	}
	
	private T[] helperAux(T[] array) {
		T[] helper = (T[]) new Comparable[array.length];
		for (int i = 0; i < array.length; i++) {
			helper[i] = array[i];
		}
		return helper;
	}
}
