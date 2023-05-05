package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import orderStatistic.KLargestOrderStatisticsImpl;

public class OutroKEstatistics {
	
	KLargestOrderStatisticsImpl<Integer> kMaiores;
										// 1 3 4 5 6
	private Integer[] arrayInteiros = {1, 5, 6, 4,  3, 10};
	private Integer[] arrayOrdenado = {2, 4, 6, 7, 8, 9};
	private Integer[] arrayInvOrd = { 6, 4, 3, 2, 0};
	private Integer[] arrayNeegativos = { -4, 5, 2, 1, 0, -3, -100};
	private Integer[] arraySoNeg = {- 4, -300, -500, -5, -30, -70, -350};
	private Integer[] vetorGeral = {0, 2, 4, 6, 0, 8, -7, 6, 5, 0, 4, -4, 4, 4, -4, 0};
	
	private Integer[] vetorVazio= {};
	private Integer[] vetorNulo = null;
	private Integer[] vetorRepetidos = {2, 2, 2, 2, 2, 2};
	
	@Before
	public void setUp() throws Exception {
		kMaiores = new KLargestOrderStatisticsImpl<Integer>();
		
	}
	
	private void genericTest(Integer[] arr, int k) {
		Integer result = kMaiores.orderStatistics(arr, k);
		
		Integer[] copy = Arrays.copyOf(arr, arr.length);
		Arrays.sort(copy);
		
		assertEquals(result, copy[k - 1]);
	}
	
	private void nullTest(Integer[] arr, int k) {
		Integer result = kMaiores.orderStatistics(arr, k);
		
		assertEquals(result, null);
	}
	
	@Test
	public void test01() {
		Integer expected = 5;
		
		genericTest(arrayInteiros, 3);
	}
	
	@Test
	public void test02() {
		Integer expected = 9;
		
		genericTest(arrayOrdenado, 1);
	}
	// FAILING!!!
	@Test
	public void test03() {
		Integer expected = 3;
		
		genericTest(arrayInvOrd, 1);
	}
	
	@Test
	public void test04() {
		Integer[] expected = {1, 5, 6, 4,  3};
		
		genericTest(arrayNeegativos, 3);
	}
	
	@Test
	public void test05() {
		Integer[] expected = {1, 5, 6, 4,  3};
		
		genericTest(arraySoNeg, 3);
	}
	
	@Test
	public void test06() {
		Integer expected = 6;
		
		genericTest(vetorGeral, 2);
	}
	@Test
	public void testMaiorQueN() {
		Integer expected = 6;
		
		nullTest(vetorGeral, 200);
	}
	@Test
	public void testMenorQue0() {
		Integer expected = 6;
		
		nullTest(vetorGeral, -2);
	}
	
	@Test
	public void testVazio() {
		Integer expected = 6;
		
		nullTest(vetorVazio, 2);
	}
	@Test
	public void testNulo() {
		Integer expected = 6;
		
		nullTest(vetorNulo, 3);
	}
	@Test
	public void testRepetido() {
		Integer expected = 6;
		
		genericTest(vetorRepetidos, 2);
	}
}