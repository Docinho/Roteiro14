package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (!node.isEmpty())
				remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (this.root.getData().equals(node.getData())) {

			BSTNode<T> auxNode = sucessor(node.getData());
			if (auxNode == null)
				auxNode = predecessor(node.getData());

			if (auxNode == null) {
				node.setData(null);
			} else {
				T auxData = (T) node.getData();
				node.setData(auxNode.getData());
				auxNode.setData(auxData);
				remove(auxNode);
			}

		} else {
			if (node.isLeaf()) {
				node.setData(null);
				rebalanceUp((BSTNode<T>) node);
			} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				node.getRight().setParent(node);
				if (node.getData().equals(node.getParent().getRight().getData()))
					node.getParent().setRight(node.getRight());
				else
					node.getParent().setLeft(node.getRight());
				rebalanceUp(node);
			} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				node.getLeft().setParent(node.getParent());
				if (node.getParent().getLeft().getData().equals(node.getData()))
					node.getParent().setLeft(node.getLeft());
				else
					node.getParent().setRight(node.getLeft());
				rebalanceUp(node);
			} else {
				BSTNode<T> sucessorNode = sucessor(node.getData());
				T auxData = (T) node.getData();
				node.setData(sucessorNode.getData());
				sucessorNode.setData(auxData);
				remove(sucessorNode);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null)
			insert(element, this.root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (node != null) {
			if (node.isEmpty()) {

				node.setData(element);
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
				rebalanceUp(node);
			} else {
				if (node.getData().compareTo(element) < 0) {
					insert(element, (BSTNode<T>) node.getRight());
				} else if (node.getData().compareTo(element) > 0) {
					insert(element, (BSTNode<T>) node.getLeft());
				}
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int i = calculateBalance(node);
			if (i < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
					rightRotation((BSTNode<T>) node.getRight());
				}
				leftRotation(node);
			} else if (i > 1) {
				if(calculateBalance((BSTNode<T>) node.getLeft()) < 0)
					leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode parent = (BSTNode) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode) parent.getParent();
		}
	}

	public void rightRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (node.getLeft() != null && !node.getLeft().isEmpty()) {
				if (node.getParent() == null)
					this.root = (BSTNode<T>) node.getLeft();
				Util.rightRotation(node);
			}
		}
	}

	public void leftRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (node.getRight() != null && !node.getRight().isEmpty()) {
				if (node.getParent() == null)

					this.root = (BSTNode<T>) node.getRight();

				Util.leftRotation(node);
			}
		}
	}

}