package com.example.server.models.tree.binarysearch;

public class AvlNode<T extends Comparable> extends BinarySearchNode<T> {

	private int balanceFactor;
	
	public AvlNode(T value) {
		super(value);
		this.setBalanceFactor(0);
		// TODO Auto-generated constructor stub
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
}
