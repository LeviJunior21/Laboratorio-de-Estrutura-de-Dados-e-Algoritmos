package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer result = -1;
		if (array != null && x != null) {
			Integer value = binarySearch(array, 0, array.length - 1, x);
			if (value.compareTo(0) == 1) {
				result = array[value - 1];
			}
			else if (value.compareTo(0) == 0){
				result = array[value];
			}
		}
		return result;
	}
	
	private Integer binarySearch(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		Integer result = -1;
		if (leftIndex <= rightIndex) {
			Integer medium = (leftIndex + rightIndex) / 2;
			if (x.compareTo(array[medium]) == 0) {
				result = medium;
			}
			else if (x.compareTo(array[medium]) == 1) {
				result = binarySearch(array, medium + 1, rightIndex, x);
			}
			else {
				result = binarySearch(array, leftIndex, medium - 1, x);
			}
		}
		return result;
	}
}
