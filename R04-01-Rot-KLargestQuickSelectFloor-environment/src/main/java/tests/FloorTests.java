package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import problems.FloorBinarySearchImpl;

public class FloorTests {

	private FloorBinarySearchImpl searcher = new FloorBinarySearchImpl();
	
	private Integer[] arrayInteiros = {1, 5, 6, 4,  3, 10};
	private Integer[] arrayOrdenado = {2, 4, 6, 7, 8, 9};
	private Integer[] arrayInvOrd = { 6, 4, 3, 2, 0};
	private Integer[] arrayNeegativos = { -4, 5, 2, 1, 0, -3, -100};
	private Integer[] arraySoNeg = {- 4, -300, -500, -5, -30, -70, -350};
	private Integer[] vetorGeral = {0, 2, 4, 6, 0, 8, -7, 6, 5, 0, 4, -4, 4, 4, -4, 0};
	private Integer[] vetorVazio= {};
	private Integer[] vetorNulo = null;
	private Integer[] vetorRepetidos = {2, 2, 2, 2, 2, 2};
	
	
	private void genericTest(Integer[] array, Integer x, Integer floorExpected) {
		Integer result = searcher.floor(array, x);
		
		assertEquals(floorExpected, result);
	}
	
	// ordenados:
	@Test
	public void testOrd01() {
		genericTest(arrayOrdenado, 5, 4);
	}
	
	@Test
	public void testOrd02() {
		genericTest(arrayOrdenado, 2, 2);
	}
	
	@Test
	public void testOrd03() {
		genericTest(arrayOrdenado, 10, 9);
	}
	
	@Test
	public void test01() {
		genericTest(arrayInteiros, 7, 6);
	}
	
	@Test
	public void test02() {
		genericTest(arrayInteiros, 5, 5);
	}

	@Test
	public void test03() {
		genericTest(arrayInteiros, 11, 10);
	}
	
	@Test
	public void testOrdemInversa() {
		genericTest(arrayInvOrd, 3, 3);
	}
	
	@Test
	public void testMenorQuePresente() {
		genericTest(vetorGeral, -50, null);
	}

	
	@Test
	public void testRepetidos1() {
		genericTest(vetorRepetidos, 2, 2);
	}
	@Test
	public void testRepetidos2() {
		genericTest(vetorRepetidos, 1, null);
	}
	@Test
	public void testRepetidos3() {
		genericTest(vetorRepetidos, 3, 2);
	}
	@Test
	public void testVazio() {
		genericTest(vetorVazio, 0, null);
	}
	
	@Test
	public void testNull() {
		genericTest(vetorVazio, 0, null);
	}
}