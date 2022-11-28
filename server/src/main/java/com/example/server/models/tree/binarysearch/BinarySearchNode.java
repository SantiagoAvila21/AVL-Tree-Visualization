package com.example.server.models.tree.binarysearch;

import com.example.server.models.tree.binaries.BinaryNode;
import com.example.server.models.trees.Node;

public class BinarySearchNode<T extends Comparable> extends BinaryNode<T>{

	public BinarySearchNode(T value) {
		super(value);
	}
	
	@Override
	public void addNode(Node<T> node) {
		if(((Comparable) node.getValue()).compareTo(this.value) > 0) {
			if(this.getNodeChilds().get(1) != null) {
				((BinarySearchNode<T>) this.getNodeChilds().get(1)).addNode(node);
			}else {
				node.setParent(this);
				this.getNodeChilds().set(1, node);
			}
		}else {
			if(this.getNodeChilds().get(0) != null) {
				((BinarySearchNode<T>) this.getNodeChilds().get(0)).addNode(node);
			}else {
				node.setParent(this);
				this.getNodeChilds().set(0, node);
			}
		}
	}
	
}
