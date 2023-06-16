package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		BSTNode<T> SS = (BSTNode<T>) pivot.getLeft();
		
		pivot.setParent(parent);
		
		if (!parent.isEmpty()) {
			if (pivot.getData().compareTo(parent.getData()) < 0) {
				parent.setLeft(pivot);
			} else {
				parent.setRight(pivot);
			}
		}
		
		pivot.setLeft(node);
		node.setParent(pivot);
		
		node.setRight(SS);
		SS.setParent(node);
		
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> root = node;
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		BSTNode<T> SS = (BSTNode<T>) pivot.getRight();
		
		pivot.setParent(parent);
		
		if (!node.getParent().isEmpty()) {
			if (pivot.getData().compareTo(parent.getData()) < 0) {
				parent.setLeft(pivot);
			} else {
				parent.setRight(pivot);
			}
		}
		
		pivot.setRight(root);
		root.setParent(pivot);
		
		root.setLeft(SS);
		SS.setParent(root);
		
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}