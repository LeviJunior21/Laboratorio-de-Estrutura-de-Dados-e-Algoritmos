package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex) {
			int menor = indiceDoMenor(array, leftIndex, leftIndex, rightIndex);
			Util.swap(array, leftIndex, menor);
			sort(array, leftIndex + 1, rightIndex);
		}
	}
	
	private int indiceDoMenor(T[] array, int menor, int inicio, int fim) {
		int indiceMenor = menor;
		if (inicio < fim) {
			if (array[inicio].compareTo(array[indiceMenor]) == -1) {
				indiceMenor = inicio;
			}
			indiceMenor = indiceDoMenor(array, indiceMenor, inicio + 1, fim);
		}
		return indiceMenor;
	}

}
