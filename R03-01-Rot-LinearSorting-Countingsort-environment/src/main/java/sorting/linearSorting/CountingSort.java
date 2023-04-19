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
		if (leftIndex < rightIndex) {
			int biggerNumber = maxNumber(array, leftIndex, rightIndex);
			int minNumber = minBetweenNumbers(array, leftIndex, rightIndex);
			
			int[] arrayCount = new int[(biggerNumber - minNumber) + 1];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				arrayCount[array[i] - minNumber] += 1;
			}
			
			int[] arrayCumulative = arrayCount;
			for (int j = leftIndex + 1; j <= arrayCumulative.length - 1; j++) {
				arrayCumulative[j] += arrayCumulative[j - 1];
			}
			
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
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(bigger) == 1) {
				bigger = array[i];
			}
		}
		return bigger;
	}

}
