package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && rightIndex < array.length){
			if (leftIndex < rightIndex) {
				int indexPivot = particiona(array, leftIndex, rightIndex);
				sort(array, leftIndex, indexPivot - 1);
				sort(array, indexPivot + 1, rightIndex);
			}
		}
	}
	
	private int particiona(T[] array, int leftIndex, int rightIndex) {
		
		int meio = (leftIndex + rightIndex) / 2;
		int predecessor= rightIndex - 1;
		
		if (array[leftIndex].compareTo(array[rightIndex]) == 1) {
			Util.swap(array, leftIndex, rightIndex);
		}
		if (array[leftIndex].compareTo(array[meio]) == 1) {
			Util.swap(array, leftIndex, meio);
		}
		if (array[meio].compareTo(array[rightIndex]) == 1) {
			Util.swap(array, meio, rightIndex);
		}
		
		T pivot = array[meio];
		Util.swap(array, meio, rightIndex - 1);
		
		int index = leftIndex + 1;
		
		while (index < predecessor) {
			if (array[index].compareTo(pivot) >= 0) {
				predecessor--;
				Util.swap(array, index, predecessor);
			} else {
				index++;
			}
		}
		
		Util.swap(array, rightIndex - 1, predecessor);
		return predecessor;
	}
}
