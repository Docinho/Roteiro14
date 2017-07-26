package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> right = node;
		if (node != null && !node.isEmpty()) {
			right = (BSTNode<T>) node.getRight();
			if (right != null && !right.isEmpty()) {
			node.setRight(right.getLeft());
			node.getRight().setParent(node);

			if (node.getParent() != null) {
				if (node.getParent().getLeft().equals(node))
					node.getParent().setLeft(right);
				else
					node.getParent().setRight(right);
			}
			right.setParent(node.getParent());

			right.setLeft(node);
			node.setParent(right);
			}
		}
		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> left = node;
		if (node != null) {
			left = (BSTNode<T>) node.getLeft();

			node.setLeft(left.getRight());
			node.getLeft().setParent(node);

			if (node.getParent() != null) {
				if (node.getParent().getLeft().equals(node))
					node.getParent().setLeft(left);
				else
					node.getParent().setRight(left);
			}
			left.setParent(node.getParent());

			node.setParent(left);
			left.setRight(node);
		}
		return left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
