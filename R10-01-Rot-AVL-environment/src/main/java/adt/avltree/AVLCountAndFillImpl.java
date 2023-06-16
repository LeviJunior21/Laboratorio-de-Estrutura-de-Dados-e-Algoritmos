package adt.avltree;

import java.util.Arrays;
import java.util.HashSet;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	
	@Override
	public void fillWithoutRebalance(T[] array) {
		HashSet<T> set = new HashSet<T>();
		for(T i : array) {
			set.add(i);
		}
		for(T i : this.order()) {
			set.add(i);
		}
		array = (T[]) set.toArray(new Comparable[set.size()]);
		Arrays.sort(array);
		this.root = new BSTNode<T>();
		
		int height = 0;
		
		while(fillWithoutRebalance(array, 0, array.length, height)) {
			height++;
		}

	}

	private boolean fillWithoutRebalance (T[] array, int left, int right, int height) {
		boolean resp = false;

		if (right > left) {
			int middle = left + (right - left) / 2;

			if (height == 0) {
				this.insert(array[middle]);
				resp = true;
			}
			else {
				boolean result1 = fillWithoutRebalance(array, left, middle, height - 1);
				boolean result2 = fillWithoutRebalance(array, middle + 1, right, height - 1);

				resp = result1 || result2;
			}
		}
		return resp;
	}
	
	
	
	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		
		if (balance > 1) {
			
			int balanceLeft = calculateBalance((BSTNode<T>) node.getLeft());
			if (balanceLeft < 0) {
				
				rotateLeft((BSTNode<T>) node.getLeft());
				rotateRight(node);
				
				this.LRcounter++;
				
			} else {
				rotateRight(node);
				this.LLcounter++;
			}
			
		} else if (balance < -1){
			
			int balanceRight = calculateBalance((BSTNode<T>) node.getRight());
			if (balanceRight > 0) {
				
				rotateRight((BSTNode<T>) node.getRight());
				rotateLeft(node);
				
				this.RLcounter++;
			} else {
				rotateLeft(node);
				
				this.RRcounter++;
			}
			
		}
		
	}
}