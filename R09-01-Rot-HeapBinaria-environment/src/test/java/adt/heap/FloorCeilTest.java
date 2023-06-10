package adt.heap;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import adt.heap.extended.FloorCeilHeapImpl;

public class FloorCeilTest {
	
	Comparator<Integer> comparator;
	FloorCeilHeapImpl fc;
	Integer[] array = new Integer[] {10,2,7,9,25,30,79};

	@Before
	public void inicial() {
		comparator = new ComparatorMinHeap<Integer>();
		fc = new FloorCeilHeapImpl(comparator);
	}
	
	@Test
	public void floor() {
		assertEquals(fc.floor(array, 2), new Integer(2));
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.floor(array, 200), new Integer(79));
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.floor(array, 0), null);
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.ceil(array, 0), new Integer(2));
		array = new Integer[] {10,2,7,9,25,30,79};
		assertEquals(fc.ceil(array, 200), null);
	}
	
	@Test
	public void nullArray() {
		assertEquals(fc.floor(null, 2), null);
		assertEquals(fc.floor(null, 200), null);
		assertEquals(fc.ceil(null, 0), null);
	}
	
	@Test
	public void floorCeilTest() {
		Integer[] array = new Integer[] {1, 9, -2, 34, 290, 29, 1, -23, 0, 25, -23
				, 23, 1, 89234, 29016, -245245, -2, 9, 80};
		
		Integer[] sortedArray = new Integer[] {1, 9, -2, 34, 290, 29, 1, -23, 0, 25, -23
				, 23, 1, 89234, 29016, -245245, -2, 9, 80};
		
		
		Arrays.sort(sortedArray);
		
		// floor ---------------
		for (int i = sortedArray[0] - 100; 
				i <= sortedArray[array.length - 1] + 100; i++) {
			assertEquals(floorAndCeil(array, i)[0],
					fc.floor(sortedArray, i));
		}
		// ceil ---------------
		for (int i = sortedArray[0] - 100; 
				i <= sortedArray[array.length - 1] + 100; i++) {
			assertEquals(floorAndCeil(array, i)[1],
					fc.ceil(sortedArray, i));
		}
		
	}
	
	public Integer[] floorAndCeil(Integer[] arr, int x)
    {
        int n = arr.length;
         
        // Indexes of floor and ceiling
        int fInd = -1, cInd = -1;
  
        // Distances of current floor and ceiling
        int fDist = Integer.MAX_VALUE, cDist = Integer.MAX_VALUE;
  
        for (int i = 0; i < n; i++)
        {
            // If current element is closer than
            // previous ceiling.
            if (arr[i] >= x && cDist > (arr[i] - x))
            {
                cInd = i;
                cDist = arr[i] - x;
            }
  
            // If current element is closer than
            // previous floor.
            if (arr[i] <= x && fDist > (x - arr[i]))
            {
                fInd = i;
                fDist = x - arr[i];
            }
        }
        
        Integer[] r = new Integer[] {null, null};
        
        if(fDist != Integer.MAX_VALUE)
        	r[0] = arr[fInd];
  
        if(cDist != Integer.MAX_VALUE)
        	r[1] = arr[cInd];
        
        return r;
    }
}