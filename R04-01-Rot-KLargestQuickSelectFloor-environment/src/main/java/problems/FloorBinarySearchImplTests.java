package problems;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
public class FloorBinarySearchImplTests {

	FloorBinarySearchImpl buscaAntecessor = new FloorBinarySearchImpl();
	Integer[] array1 = {1,2,3,4,5};	
	
	@Test
	public void test() {
		Integer x = 5;
		Integer a = 4;
		assertEquals(a, buscaAntecessor.floor(array1, x));
	}

}
