package com.example.server.models.trees;

import java.util.ArrayList;

public abstract class Node<T>{

	protected T value;
	protected ArrayList<Node<T>> nodeChilds;
	protected Node<T> parent = null;
	
	public Node(T value, Node<T> parent) {
		this.value = value;
		this.nodeChilds = new ArrayList<Node<T>>();
		this.parent = parent;
	}
	
	public Node(T value) {
		this.value = value;
		this.nodeChilds = new ArrayList<Node<T>>();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ArrayList<Node<T>> getNodeChilds() {
		return nodeChilds;
	}

	public void setNodeChilds(ArrayList<Node<T>> nodeChilds) {
		this.nodeChilds = nodeChilds;
	}
	
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	public Node<T> getParent(){
		return this.parent;
	}

	public abstract void addNode(Node<T> node);

	
	
}
