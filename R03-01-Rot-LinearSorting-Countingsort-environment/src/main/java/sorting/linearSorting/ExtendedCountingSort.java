package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && leftIndex < rightIndex) {
			Integer biggerNumber = maxBetweenNumbers(array, leftIndex, rightIndex);
			Integer minNumber = minBetweenNumbers(array, leftIndex, rightIndex);
			
			Integer[] arrayCount = arrayZeros((biggerNumber - minNumber) + 1);
			for (int i = leftIndex; i <= rightIndex; i++) {
				arrayCount[array[i] - minNumber] += 1;
			}
			
			Integer[] arrayCumulative = arrayCount;
			for (int j = leftIndex + 1; j <= arrayCumulative.length - 1; j++) {
				arrayCumulative[j] += arrayCumulative[j - 1];
			}
			
			Integer[] helper = helperCopy(array, leftIndex, rightIndex); 
			for (int k = rightIndex; k >= leftIndex; k--) {
				array[arrayCumulative[helper[k] - minNumber] - 1] = helper[k];
				arrayCumulative[helper[k] - minNumber]--;
			}
		}
	}

	private Integer minBetweenNumbers(Integer[] array, int leftIndex, int rightIndex) {
		int min = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(min) == -1) {
				min = array[i];
			}
		}
		return min;
	}
	
	private Integer maxBetweenNumbers(Integer[] array, int leftIndex, int rightIndex) {
		int bigger = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(bigger) == 1) {
				bigger = array[i];
			}
		}
		return bigger;
	}
	
	private Integer[] arrayZeros(int size) {
		 Integer[] newArray = new Integer[size];
		 for (int i = 0; i <= size - 1; i++) {
			 newArray[i] = 0;
		 }
		 return newArray;
	}
	
	private Integer[] helperCopy(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] copyArray = new Integer[array.length];
		for (int i = leftIndex; i <= rightIndex; i++) {
			copyArray[i] = array[i];
		}
		return copyArray;
	}
}
