package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

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

		if (!this.isEmpty()) {
			T[] a = (T[]) new Comparable[array.length + size()];
			a = this.preOrder();
			T[] a1 = Arrays.copyOf(array, array.length + size());

			for (int i = array.length, j = 0; i < a1.length && j < a.length; i++, j++)
				a1[i] = a[j];

			array = a1;
			this.root = new BSTNode<T>();
		}

		Arrays.sort(array);

		int index = array.length / 2;
		this.insert(array[index]);
		for (int i = 0; i < array.length; i++) {
			insere(array, 0, index);
			insere(array, index + 1, array.length );
		}

	}
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int i = calculateBalance(node);
			if (i < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
					rightRotation((BSTNode<T>) node.getRight());
					RLcounter++;
				} else {
					RRcounter++;
				}
				leftRotation(node);
			} else if (i > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
					leftRotation((BSTNode<T>) node.getLeft());
					LRcounter++;
				} else
					LLcounter++;
				rightRotation(node);

			}
		}
	}


	private void insere(T[] array, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int mid = (endIndex + startIndex) / 2;
			if (array[mid] != null) {
				insert(array[mid]);
				array[mid] = null;
			} else {
				insere(array, startIndex, mid);
				insere(array, mid + 1, endIndex);
			}
		}

	}

}
