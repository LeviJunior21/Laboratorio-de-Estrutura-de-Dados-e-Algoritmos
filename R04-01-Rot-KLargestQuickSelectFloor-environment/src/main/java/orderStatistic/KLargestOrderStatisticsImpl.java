package orderStatistic;

import java.util.Arrays;

import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		if (array != null && k >= 1 && k <= array.length && array.length != 0) {

			orderStatistics(array, array.length - k + 1);
			
			int index = 0;
			T[] biggers = (T[]) new Comparable[k];
			for (int i = array.length - k; i < array.length; i++) {
				biggers[index] = array[i];
				index++;
			}
			return biggers;
		}
		else if (array == null) {
			return (T[]) new Comparable[0];
		}
		else if (array.length == 0) {
			return array;
		}
		else {
			return Arrays.copyOfRange(array, 0, 0);
		}
	}
	
	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if (array != null && k >= 1 && k <= array.length && array.length != 0) {
			return quickSelect(array, 0, array.length - 1, k);
		} 
		else {
			return null;
		}
	}
	
	private T quickSelect(T[] array, int leftIndex, int rightIndex, int k) {
		T result = null;
		if (leftIndex <= rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			if (pivot == k - 1) {
				result = array[pivot];
			}
			else if (pivot < k - 1) {
				result = quickSelect(array, pivot + 1, rightIndex, k);
			}
			else {
				result = quickSelect(array, leftIndex, pivot - 1, k);
			}
		}
		return result;
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
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
}
