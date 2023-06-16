package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return isBST(bst.getRoot(), null, null);
	}
	
	protected boolean isBST(BSTNode<T> node, T min, T max) {
		boolean result = true;
		
		if (!node.isEmpty()) {
			if (min != null && node.getData().compareTo(min) < 0
					|| max != null && node.getData().compareTo(max) > 0) {
				
				result = false;
			}
			
			result = result && isBST((BSTNode<T>) node.getLeft(), min, node.getData())
							&& isBST((BSTNode<T>) node.getRight(), node.getData(), max);
		}
		
		return result;
	}
	
}