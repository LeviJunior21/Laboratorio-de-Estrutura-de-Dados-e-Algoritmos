package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
<<<<<<< HEAD
		if (leftIndex < rightIndex) {
			int biggerNumber = maxNumber(array, leftIndex, rightIndex);
			int minNumber = minBetweenNumbers(array, leftIndex, rightIndex);
			
			int[] arrayCount = new int[(biggerNumber - minNumber) + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				arrayCount[array[i] - minNumber] += 1;
			}
			
			int[] arrayCumulative = arrayCount;
=======
		if (array != null && leftIndex >= 0 && leftIndex < rightIndex && rightIndex < array.length) {
			Integer biggerNumber = maxBetweenNumbers(array, leftIndex, rightIndex);
			Integer[] arrayCount = arrayZeros(biggerNumber + 1);
			for (int i = leftIndex; i <= rightIndex; i++) {
				arrayCount[array[i]] += 1;
			}
			
			Integer[] arrayCumulative = arrayCount;
>>>>>>> 9f505c11883f43538ddd10660c9f14757eac74ec
			for (int j = leftIndex + 1; j <= arrayCumulative.length - 1; j++) {
				arrayCumulative[j] += arrayCumulative[j - 1];
			}
			
<<<<<<< HEAD
			int[] arrayOrder = new int[array.length];
			for (int k = array.length - 1; k >= leftIndex; k--) {
				arrayOrder[arrayCumulative[array[k] - minNumber] - 1] = array[k];
				arrayCumulative[array[k] - minNumber]--;
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
	
	private Integer maxNumber(Integer[] array, int leftIndex, int rightIndex) {
		int bigger = array[leftIndex];
=======
			Integer[] helper = helperCopy(array, leftIndex, rightIndex);
			for (int k = rightIndex; k >= leftIndex; k--) {
				array[arrayCumulative[helper[k]] - 1] = helper[k];
				arrayCumulative[helper[k]]--;
			}
		}
	}
	
	private Integer maxBetweenNumbers(Integer[] array, int leftIndex, int rightIndex) {
		Integer bigger = array[leftIndex];
>>>>>>> 9f505c11883f43538ddd10660c9f14757eac74ec
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(bigger) == 1) {
				bigger = array[i];
			}
		}
		return bigger;
	}
<<<<<<< HEAD

=======
	
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
>>>>>>> 9f505c11883f43538ddd10660c9f14757eac74ec
}
