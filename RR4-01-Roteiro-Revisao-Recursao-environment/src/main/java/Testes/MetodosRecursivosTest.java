package Testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import recursao.MetodosRecursivos;

public class MetodosRecursivosTest {
	
	MetodosRecursivos metodos;
	
	@Before
	public void setup() {
		metodos = new MetodosRecursivos();
	}
	
	@Test
	public void testaFatorialInteiroMaiorQueUm() {
		assertEquals(120, metodos.calcularFatorial(5));
	}

	@Test
	public void testaFatorialInteiroUm() {
		assertEquals(1, metodos.calcularFatorial(1));
	}
	
	@Test
	public void testaFatorialNulo() {
		assertEquals(1, metodos.calcularFatorial(0));
	}
	
	@Test
	public void testaSomaArray() {
		int[] arrayInteiro = {1,2,3,4,5};
		assertEquals(15, metodos.calcularSomaArray(arrayInteiro));
	}
	
	@Test
	public void contaNenhumNaoNull() {
		Object[] objeto = {null};
		assertEquals(0, metodos.countNotNull(objeto));
	}
	
	@Test
	public void contaUmNenhumNull() {
		Object[] objeto = {1};
		assertEquals(1, metodos.countNotNull(objeto));
	}
	
	@Test
	public void contaVariosNaoNull() {
		Object[] objeto = {1,2,3,4,null,1,1,null};
		assertEquals(6, metodos.countNotNull(objeto));
	}
	
	@Test
	public void contaVariosNaoNullComNullNasExtremidades() {
		Object[] objeto = {null,2,3,4,null,1,1,null};
		assertEquals(5, metodos.countNotNull(objeto));
	}
	
	@Test
	public void contaVariosNaoNullComNullNoInicio() {
		Object[] objeto = {null,2,3,4};
		assertEquals(3, metodos.countNotNull(objeto));
	}
	
	@Test
	public void contaVariosNaoNullComNullNoFim() {
		Object[] objeto = {2,3,4,null};
		assertEquals(3, metodos.countNotNull(objeto));
	}
	
	@Test
	public void calculaPotenciaDe2ElevadoAZero() {
		assertEquals(1, metodos.potenciaDe2(0));
	}
	
	@Test
	public void calculaPotenciaDe2ElevadoAUm() {
		assertEquals(2, metodos.potenciaDe2(1));
	}
	
	@Test
	public void calculaPotenciaDe2ElevadoAUmValorMaior() {
		assertEquals(16, metodos.potenciaDe2(4));
	}
	
	@Test
	public void testeProgressaoAritmetica() {
		assertEquals(13.0, metodos.progressaoAritmetica(7.0, 3.0, 2), 1);
	}
	
	@Test
	public void testeProgressaoAritmetica2() {
		assertEquals(10, metodos.progressaoAritmetica(7.0, 3.0, 1), 1);
	}
	
	
	@Test
	public void testProgressaoGeometrica() {
		assertEquals(27.0, metodos.progressaoGeometrica(3.0, 3.0, 2), 1);
	}
	
	@Test
	public void calcularFibonacci() {
		assertEquals(1, metodos.calcularFibonacci(2));
	}
}
