package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;

public class AVLCountAndFill<T extends Comparable<T>> extends AVLTreeImpl<T> {

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
				if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
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
		int index = array.length / 2;
		T root1 = null;
		if (root.getData() != null && array[index].compareTo(root.getData()) > 0) {
			root1 = root.getData();
			root.setData(array[index]);
			insert(root1);
		} else
			insert(array[index]);
		for (int i = 0; i < array.length; i++) {
			insere(array, 0, index);
			insere(array, index + 1, array.length - 1);
		}
	}

	private void insere(T[] array, int index0, int index1) {
		if (index0 < index1) {
			int mid = (int) Math.ceil((index1 + index0) / 2);
			if (array[mid] != null) {
				insert(array[mid]);
				array[mid] = null;
			} else {
				insere(array, index0, mid);
				insere(array, mid + 1, index1);
			}
		}

	}
}
