package com.example.server.models.trees;

public abstract class Tree <T>{
	protected Node<T> root = null;
	
	public abstract void addNode(T value);

	public abstract void addNode(Node<T> node);
	
	public abstract void deleteNode(Node<T> node);
		
	public Node<T> getRoot(){
		return this.root;
	}
	
	public void setRoot(Node<T> root) {
		this.root = root;
	}
}
