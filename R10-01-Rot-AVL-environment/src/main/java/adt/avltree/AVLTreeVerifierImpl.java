package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return isBST() && isAVL(avlTree.getRoot());
	}
	
	private boolean isAVL(BSTNode<T> node) {
		boolean result = true;
		
		if (!node.isEmpty()) {
			result = Math.abs(height((BSTNode<T>) node.getLeft()) 
								- height((BSTNode<T>) node.getRight())) <= 1;
			
			result = result && isAVL((BSTNode<T>) node.getLeft()) 
					&& isAVL((BSTNode<T>) node.getRight());
		}
		
		return result;
	}
	
	private int height(BSTNode<T> node) {
		int result;
		
		if (node.isEmpty()) {
			result = -1;
		} else {
			result = 1 + Math.max(height((BSTNode<T>) node.getLeft()), 
									height((BSTNode<T>)node.getRight()));
		}
		
		return result;
	}

}