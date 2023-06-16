package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) 
				- height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		
		if (balance > 1) {
			
			int balanceLeft = calculateBalance((BSTNode<T>) node.getLeft());
			if (balanceLeft < 0) {
				rotateLeft((BSTNode<T>) node.getLeft());
			}
				
			rotateRight(node);
			
		} else if (balance < -1){
			
			int balanceRight = calculateBalance((BSTNode<T>) node.getRight());
			if (balanceRight > 0) {
				rotateRight((BSTNode<T>) node.getRight());
			}
			
			rotateLeft(node);
		}
		
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (!node.isEmpty()) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
	
	@Override
	public void insert(T element) {
		insert(root, element, new BSTNode<T>());
	}
	
	private void insert(BSTNode<T> node, T element, BSTNode<T> parent) {
		if((element != null) && (node.isEmpty())) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);

			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}

		else {
			if (element.compareTo(node.getData()) < 0) {
				this.insert((BSTNode<T>) node.getLeft(), element, node);
			} else {
				this.insert((BSTNode<T>) node.getRight(), element, node);
			}
		}

		this.rebalanceUp((BSTNode<T>) node.getParent());	
	}
	
	@Override
	public void remove(T element) {
		if (element != null) {
			this.remove(super.search(element));
		}
	}
	
	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if(node.isLeaf()) {
				if(node.equals(root) && root.isLeaf()) {
					root.setData(null);
				}
				else {
					if(node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(new BSTNode<T>());
					}else {
						node.getParent().setRight(new BSTNode<T>());
					}
				}
				this.rebalanceUp(node);
			}
			
			else if(!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				if(node.getLeft().equals(root.getLeft())) {
					root = (BSTNode<T>) node.getLeft();
					root.setParent(new BSTNode<T>());
				}
				else {
					node.getLeft().setParent(node.getParent());
					if(node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(node.getLeft());
					}else {
						node.getParent().setRight(node.getLeft());
					}
				}
				
				this.rebalanceUp(node);
				
			}
			
			else if(node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				if(node.getRight().equals(root.getRight())) {
					root = (BSTNode<T>) node.getRight();
					root.setParent(null);
				}
				else {
					node.getRight().setParent(node.getParent());
					if(node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(node.getRight());
					}else {
						node.getParent().setRight(node.getRight());
					}
				}
				
				this.rebalanceUp(node);
			}
			
			else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
	}
	
	
	protected void rotateRight(BSTNode<T> node) {
		BSTNode<T> pivot = Util.rightRotation(node);
		
		if (node.equals(root)) {
			this.root = pivot;
		}
	}
	
	protected void rotateLeft(BSTNode<T> node) {
		BSTNode<T> pivot = Util.leftRotation(node);
		
		if (node.equals(root)) {
			this.root = pivot;
		}
	}
}
