package com.example.server.models.tree.binaries;

import com.example.server.models.trees.Node;

public abstract class BinaryNode<T> extends Node<T>{
	
	public BinaryNode(T value) {
		super(value);
		this.nodeChilds.add(null);
		this.nodeChilds.add(null);
	}
	
	public BinaryNode(T value, Node<T> parent) {
		super(value, parent);
		this.nodeChilds.add(null);
		this.nodeChilds.add(null);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	public void InfixRoute() {
		if(this.getNodeChilds().get(0) != null) {
			((BinaryNode<T>) (this.getNodeChilds().get(0))).InfixRoute();
		}
		System.out.println(this.toString());
		if(this.getNodeChilds().get(1) != null) {
			((BinaryNode<T>) (this.getNodeChilds().get(1))).InfixRoute();
		}
	}

	public void PosfixRoute() {
		if(this.getNodeChilds().get(0) != null) {
			((BinaryNode<T>) this.getNodeChilds().get(0)).InfixRoute();
		}
		if(this.getNodeChilds().get(1) != null) {
			((BinaryNode<T>) this.getNodeChilds().get(1)).InfixRoute();
		}
		System.out.println(this.toString());
	}
	
}
