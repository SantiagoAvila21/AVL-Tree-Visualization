package com.example.server.models.tree.binaries;

import com.example.server.models.trees.Tree;

public abstract class BinaryTree<T> extends Tree<T> {

	public void InfixRoute() {
		if(root != null) {
			((BinaryNode<T>)root).InfixRoute();
		}
	}
}
