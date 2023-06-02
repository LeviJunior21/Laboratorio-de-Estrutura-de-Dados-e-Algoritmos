package adt.bst;

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
		return this.height(this.root);
	}
	
	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}
		BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
		BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
		
		return 1 + Math.max(this.height(leftNode), this.height(rightNode));
	}
	
	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> found = new BSTNode<T>();
		if (element != null) {
			found = this.search(element, this.root);
		}
		return found;
	}

	private BSTNode<T> search(T element, BSTNode<T> aux) {
		BSTNode<T> found = new BSTNode<T>();
		if (aux.getData() != null && element.equals(aux.getData())) {
			found = aux;
		}
		else if (aux.getLeft() != null && element.compareTo(aux.getData()) == -1) {
			found = this.search(element, (BSTNode<T>) aux.getLeft());
		}
		else if (aux.getLeft() != null && element.compareTo(aux.getData()) == 1) {
			found = this.search(element, (BSTNode<T>) aux.getRight());
		}
		return found;
	}
	
	@Override
	public void insert(T element) {
		BSTNode<T> newNode = new BSTNode<T>();
		newNode.setData(element);
		newNode.setLeft(new BSTNode<T>());
		newNode.setRight(new BSTNode<T>());
		
		if (this.root.isEmpty()) {
			this.root = newNode;
			newNode.setParent(this.root);
			newNode.getLeft().setParent(this.root);
			newNode.getRight().setParent(this.root);
		}
		else {
			this.insert(this.root, element);
		}
	}
	
	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
		}
		
		else {
			if (element.compareTo(node.getData()) == -1) {
				this.insert((BSTNode<T>) node.getLeft(), element);
			}
			else if (element.compareTo(node.getData()) == 1) {
				this.insert((BSTNode<T>) node.getRight(), element);				
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> found = null;
		if (!this.isEmpty()) {
			found = this.maximum(this.root);
		}
		return found;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> found = node;
		BSTNode<T> nodeRight = (BSTNode<T>) node.getRight();
		if (!nodeRight.isEmpty()) {
			found = this.maximum(nodeRight);
		}
		return found;
	}
	
	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> found = null;
		if (!this.isEmpty()) {
			found = this.minimum(this.root);
		}
		return found;
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> found = node;
		BSTNode<T> nodeLeft= (BSTNode<T>) node.getLeft();
		if (!nodeLeft.isEmpty()) {
			found = this.maximum(nodeLeft);
		}
		return found;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> found = this.search(element);
		BSTNode<T> result = null;
		if (!found.isEmpty()) {
			if (!found.getRight().isEmpty()) {
				result = this.minimum((BSTNode<T>) found.getRight());
			}
			else {
				result = this.sucessorParent(element, (BSTNode<T>) found.getParent());
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
		BSTNode<T> found = this.search(element);
		BSTNode<T> result = null;
		if (!found.isEmpty()) {
			if (!found.getLeft().isEmpty()) {
				result = this.minimum((BSTNode<T>) found.getLeft());
			}
			else {
				result = this.predecessorParent(element, (BSTNode<T>) found.getParent());
			}
		}
		return result;
	}
	
	private BSTNode<T> predecessorParent(T element, BSTNode<T> current) {
		BSTNode<T> result;
		if (current == null || current.getData().compareTo(element) == -1) {
			result = current;
		} else {
			result = predecessorParent(element, (BSTNode<T>) current.getParent());
		}
		
		return result;
	}

	@Override
	public void remove(T element) {
		if (element != null && this.root != null) {
			if (element.equals(this.root.getData()) && this.root.isLeaf()) {
				this.root = new BSTNode<T>();
			}
			else {
				BSTNode<T> found = this.search(element);
				BSTNode<T> NIL = new BSTNode<T>();
				if (found != NIL) {
					this.remove(this.root, found);
				}
			}
		}
	}
	
	private void remove(BSTNode<T> node, BSTNode<T> found) {
		if (node.isLeaf()) {
			found.setData(node.getData());
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			if (node.getData().compareTo(parent.getData()) == 1) {
				parent.setLeft(new BSTNode<T>());
			}
			else {
				parent.setRight(new BSTNode<T>());
			}
		}
	
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
