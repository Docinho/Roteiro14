package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;

public class AVLCountAndFill <T extends Comparable<T>> extends AVLTreeImpl<T> {
	
	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;
	


	public int LLcounter() {
		return LLcounter;
	}

	public int LRcounter() {
		return LRcounter;
	}

	public int RRcounter() {
		return RRcounter;
	}

	public int RLcounter() {
		return RLcounter;
	}
	
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int i = calculateBalance(node);
			if (i < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
					rightRotation((BSTNode<T>) node.getRight());
					RLcounter++;
				} else {
					LLcounter++;
				}
				leftRotation(node);
			} else if (i > 1) {
				if(calculateBalance((BSTNode<T>) node.getLeft()) < 0){
					leftRotation((BSTNode<T>) node.getLeft());
					LRcounter++;
				} else
					RRcounter++;
				rightRotation(node);
				
			}
		}
	}
	
	public void fillWithoutRebalance(T[] array) {
		Arrays.sort(array);
		int index = array.length/2;
				
		for(int i = 0; i < array.length; i++) {
			index = (int) (index + ((Math.pow(-1,i)*i)));
			this.insert(array[index]);
		}
	}
}
