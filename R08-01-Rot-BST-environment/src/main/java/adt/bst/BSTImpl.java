package adt.bst;

import java.util.ArrayList;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}
	
	private int height(BSTNode<T> node) {
		if (node.isEmpty()) return -1;
		
		BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
		
		return 1 + Math.max(height(leftNode), height(rightNode));
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> current) {
		BSTNode<T> result = new BSTNode<T>();

		if (current.isEmpty() ||current.getData().equals(element)) {
			result = current;
		} else {
			if (element.compareTo(current.getData()) < 0) {
				result = search(element, (BSTNode<T>) current.getLeft());
			} else {
				result = search(element, (BSTNode<T>) current.getRight());
			}
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (this.isEmpty()) {			
			this.root = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.left(new BSTNode<T>())
						.right(new BSTNode<T>())
						.build();
								
			this.root.getLeft().setParent(root);
			
			this.root.getRight().setParent(root);
		} else {
			this.insert(root, element);
		}
		
	}
	
	private void insert(BSTNode<T> node,T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
		} else if (element.compareTo(node.getData()) < 0) {
			this.insert((BSTNode<T>) node.getLeft(), element);
		} else {
			this.insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		
		if (!this.isEmpty()) {
			result = this.maximum(this.root);
		}
		
		return result;
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = node;
		
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if(!right.isEmpty()) {
			result = maximum(right);
		}
		
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		
		if (!this.isEmpty()) {
			result = this.minimum(this.root);
		}
		
		return result;
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = node;
		
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		if(!left.isEmpty()) {
			result = minimum(left);
		}
		
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		
		BSTNode<T> result = null;
		
		if (!node.isEmpty()) {
			BSTNode<T> right = (BSTNode<T>) node.getRight();
			if(!right.isEmpty()) {
				result = minimum(right);
			} else {
				result = sucessorParent(element, (BSTNode<T>) node.getParent());
			}
		}
		
		return result;
	}
	
	private BSTNode<T> sucessorParent(T element, BSTNode<T> current) {
		BSTNode<T> result;
		if (current == null || current.getData().compareTo(element) > 0) {
			result = current;
		} else {
			result = sucessorParent(element, (BSTNode<T>) current.getParent());
		}
		
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
				
		BSTNode<T> result = null;
		
		if (!node.isEmpty()) {
			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			if(!left.isEmpty()) {
				result = maximum(left);
			} else {
				result = predecessorParent(element, (BSTNode<T>) node.getParent());
			}
		}
		
		return result;	
	}
	
	private BSTNode<T> predecessorParent(T element, BSTNode<T> current) {
		BSTNode<T> result = null;		
		
		if (current == null || current.getData().compareTo(element) < 0) {
			result = current;
		} else {
			result = predecessorParent(element, (BSTNode<T>) current.getParent());
		}
				
		return result;
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
				
		if (!node.isEmpty()){
			if (node.isLeaf()) {
				if (node == root) {
					root =  new BSTNode<T>();
				} else {
					node.setData(null);
					node.setLeft(null);
					node.setRight(null);
				}
			} else if (node.getRight().isEmpty()) {
				if (node == root) {
					root = (BSTNode<T>) root.getLeft();
					root.setParent(null);
				} else {
					BSTNode<T> parent = (BSTNode<T>) node.getParent();
					if (node.getLeft().getData().compareTo(parent.getData()) < 0) {
						parent.setLeft(node.getLeft());
					} else {
						parent.setRight(node.getLeft());
					}
					
					node.getLeft().setParent(parent);
				}
			} else if (node.getLeft().isEmpty()) {
				if (node == root) {
					root = (BSTNode<T>) root.getRight();
					root.setParent(null);
				} else {
					BSTNode<T> parent = (BSTNode<T>) node.getParent();
					if (node.getRight().getData().compareTo(parent.getData()) < 0) {
						parent.setLeft(node.getRight());
					} else {
						parent.setRight(node.getRight());
					}
					
					node.getRight().setParent(parent);
				}
			} else {
				T toRemoveElement = minimum((BSTNode<T>) node.getRight()).getData();
				remove(toRemoveElement);
				node.setData(toRemoveElement); 
			}
		} 
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> result = new ArrayList<T>();
		
		preOrder(this.root, result);
		
		return (T[]) result.toArray(new Comparable[this.size()]);
	}
	
	private void preOrder(BSTNode current, ArrayList<T> lista) {
		if (!current.isEmpty()) {
			lista.add((T) current.getData());
			preOrder((BSTNode<T>) current.getLeft(), lista);
			preOrder((BSTNode<T>) current.getRight(), lista);
		}
	}
	
	@Override
	public T[] order() {
		ArrayList<T> result = new ArrayList<T>();
		
		order(this.root, result);
		
		return (T[]) result.toArray(new Comparable[this.size()]);
	}
	
	private void order(BSTNode current, ArrayList<T> lista) {
		if (!current.isEmpty()) {
			order((BSTNode<T>) current.getLeft(), lista);
			lista.add((T) current.getData());
			order((BSTNode<T>) current.getRight(), lista);
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> result = new ArrayList<T>();
		
		postOrder(this.root, result);
		
		return  (T[]) result.toArray(new Comparable[this.size()]);
	}
	
	private void postOrder(BSTNode current, ArrayList<T> lista) {
		if (!current.isEmpty()) {
			postOrder((BSTNode<T>) current.getLeft(), lista);
			postOrder((BSTNode<T>) current.getRight(), lista);
			lista.add((T) current.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}