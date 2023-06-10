package adt.heap;

import org.junit.Before;
import org.junit.Test;

import orderStatistic.OrderStatisticsHeapImpl;

import static org.junit.Assert.*;

import java.util.Arrays;

public class OrderStatisticTest {

    private OrderStatisticsHeapImpl<Integer> orderStatisticsHeap;

    @Before
    public void criaClasses () {
        this.orderStatisticsHeap = new OrderStatisticsHeapImpl<>();
    }

    @Test
    public void testEstatisticaDeOrdemDeArrayVazio () {
    	assertEquals(null, this.orderStatisticsHeap.getOrderStatistics(new Integer[]{}, 1));
    	assertEquals(null, this.orderStatisticsHeap.getOrderStatistics(null, 1));
    }

    @Test
    public void testEstatisticaDeOrdemComPosicaoForaDoArray () {
        Integer[] array = new Integer[]{4,8,3,7,6};
        
        assertEquals(null, this.orderStatisticsHeap.getOrderStatistics(array, 0));
        assertEquals(null, this.orderStatisticsHeap.getOrderStatistics(array, -1));
        assertEquals(null, this.orderStatisticsHeap.getOrderStatistics(array, 6));
        assertEquals(null, this.orderStatisticsHeap.getOrderStatistics(array, 10));
    }

	@Test
    public void testEstatisticaDeOrdem01 () {
        Integer[] array = new Integer[]{4,8,-3,7,6}; // {3,4,6,7,8}
        
        assertEquals(new Integer(8), this.orderStatisticsHeap.getOrderStatistics(array, 5));
        assertEquals(new Integer(6), this.orderStatisticsHeap.getOrderStatistics(array, 3));
        assertEquals(new Integer(4), this.orderStatisticsHeap.getOrderStatistics(array, 2));
        assertEquals(new Integer(7), this.orderStatisticsHeap.getOrderStatistics(array, 4));
        assertEquals(new Integer(-3), this.orderStatisticsHeap.getOrderStatistics(array, 1));
        
    }

    @Test
    public void testEstatisticaDeOrdem02 () {
        Integer[] array = new Integer[]{1};

        assertEquals(new Integer(1), this.orderStatisticsHeap.getOrderStatistics(array, 1));
    }
    
    @Test
	public void testGeral() {
		Integer[] array = new Integer[] {1, 9, -2, 34, 290, 29, 1, -23, 0, 25, -23
				, 23, 1, 89234, 29016, -245245, -2, 9, 80};
		
		Integer[] array2 = new Integer[] {1, 9, -2, 34, 290, 29, 1, -23, 0, 25, -23
				, 23, 1, 89234, 29016, -245245, -2, 9, 80};
		
		Arrays.sort(array);
		
		for (int i = 0; i < array.length; i++) {
			assertEquals( array[i],
					this.orderStatisticsHeap.getOrderStatistics(array2, i + 1));
		}
		
	}

}