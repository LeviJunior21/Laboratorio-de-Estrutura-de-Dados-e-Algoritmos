package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import orderStatistic.KLargestOrderStatisticsImpl;

public class KLarg{
	
	KLargestOrderStatisticsImpl<Integer> kMaiores;
										// 1 3 4 5 6
	private Integer[] arrayInteiros = {1, 5, 6, 4,  3, 10};
	private Integer[] arrayOrdenado = {2, 4, 6, 7, 8, 9};
	private Integer[] arrayInvOrd = { 6, 4, 3, 2, 0};
	private Integer[] arrayNeegativos = { -4, 5, 2, 1, 0, -3, -100};
	private Integer[] arraySoNeg = {- 4, -300, -500, -5, -30, -70, -350};
	private Integer[] vetorGeral = {0, 2, 4, 6, 0, 8, -7, 6, 5, 0, 4, -4, 4, 4, -4, 0};
	private Integer[] vetorProg = {4, 2, 5, 6, 3, 1, -30};
	
	
	private Integer[] vetorRepetido = {5, 5, 5, 5, 5, 5, 5, 5};
	private Integer[] vetorVazio = {};
	
	@Before
	public void setUp() throws Exception {
		kMaiores = new KLargestOrderStatisticsImpl<Integer>();
		
	}
	
	private void KLargestTest(Integer[] arr, int k) {
		Comparable[] kLargest = kMaiores.getKLargest(arr, k);
		
		System.out.println(Arrays.toString(kLargest));
		
		Comparable[] copy = Arrays.copyOf(arr, arr.length);
		Arrays.sort(copy);
		
		Comparable[] resultExpected = Arrays.copyOfRange(copy, arr.length - k, arr.length);
		Comparable[] result =  Arrays.copyOf(kLargest, kLargest.length);
		
		Arrays.sort(result);
		
		assertArrayEquals(resultExpected, result);
	}
	
	private void emptyTest(Integer[] arr, int k) {
		Integer[] kLargest = kMaiores.getKLargest(arr, k);
		
		assertArrayEquals(new Integer[0], kLargest);
	}
	
	@Test
	public void test01() {
		KLargestTest(arrayInteiros, 3);
	}
	
	@Test
	public void test02() {
		KLargestTest(arrayOrdenado, 3);
	}
	
	@Test
	public void test03() {
		KLargestTest(arrayInvOrd, 3);
	}
	
	@Test
	public void test04() {
		KLargestTest(arrayNeegativos, 3);
	}
	
	@Test
	public void test05() {
		KLargestTest(arraySoNeg, 3);
	}
	
	@Test
	public void test06() {
		KLargestTest(vetorGeral, 3);
	}
	
	@Test
	public void test07() {
		KLargestTest(vetorVazio, 0);
	}
	
	@Test
	public void test010() {
		KLargestTest(vetorRepetido, 3);
	}
	
	@Test
	public void test08() {
		KLargestTest(arrayInteiros, 6);
	}
	
	@Test
	public void test09() {
		KLargestTest(vetorProg, 3);
	}
	
	@Test
	public void test11() {
		KLargestTest(vetorProg, vetorProg.length);
	}
	
	@Test
	public void test12() {
		emptyTest(vetorProg, -1);
	}
	
	@Test
	public void testNull() {
		Comparable[] kLargest = kMaiores.getKLargest(null, 2);
		
		Comparable[] result = new Comparable[0];
		
		assertArrayEquals(result, kLargest);
		
	}
}