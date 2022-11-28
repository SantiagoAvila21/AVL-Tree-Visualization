package com.example.server.models.tree.binarysearch;

import java.util.ArrayList;

import com.example.server.models.tree.binaries.BinaryTree;
import com.example.server.models.trees.Node;


public class BinarySearchTree <T extends Comparable> extends BinaryTree<T>{

	protected ArrayList<Node> childs = new ArrayList<Node>();
	
	@Override
	public void addNode(T dato) {
		BinarySearchNode<T> newNode = new BinarySearchNode<T>(dato);
		addNode(newNode);
	}
	
	@Override
	public void addNode(Node<T> node) {
		if(root != null) {
			root.addNode(node);
		}else {
			root = node;
		}
		childs.add(node);
	}
	
	public ArrayList<Node> getChilds() {
		return childs; 
	}
	
	public Node<T> findNode(T dato) {
		for(Node<T> node: childs) {
			if(node.getValue().equals(dato)) {
				return node;
			}
		}
		return null;
	}
	
	@Override
	public void deleteNode(Node<T> node) {
		// El caso se puede dar si le mandamos un findNode con algun nodo no existente
		if(node == null) return;
				
		// Eliminamos el nodo de nuestro atributo de hijos 
		this.childs.remove(node);
		
		Node<T> candidate = node.getNodeChilds().get(0);
		if(candidate == null) {
			candidate = node.getNodeChilds().get(1);
			
			// No tendra ningun hijo por lo cual es una hoja
			if(candidate == null) {
				node.getParent().getNodeChilds().set(node.getParent().getNodeChilds().indexOf(node), null);
				return;
			}else {
				// El candidato al cambio sera el hijo(Subarbol) directo por la derecha
				candidate.setParent(node.getParent());
				if(candidate.getParent() != null) candidate.getParent().getNodeChilds().set(candidate.getParent().getNodeChilds().indexOf(node), candidate);
				else this.setRoot(candidate);
				return;
			}
		}else {
			// Tomar el nodo mas cercano por la derecha como candidato
			while(candidate.getNodeChilds().get(1) != null) {
				candidate = (Node)candidate.getNodeChilds().get(1);
			}
		}
		if(candidate.getParent().getNodeChilds().indexOf(candidate) == 0) candidate.getParent().getNodeChilds().set(0, candidate.getNodeChilds().get(0));
		else candidate.getParent().getNodeChilds().set(1, candidate.getNodeChilds().get(0));
				
		// Seteamos los parent de los hijos del nodo eliminado a candidate
		if(node.getNodeChilds().get(0) != null) node.getNodeChilds().get(0).setParent(candidate);
		if(node.getNodeChilds().get(1) != null) node.getNodeChilds().get(1).setParent(candidate);
		
		// Seteamos los parent de los hijos del candidato al padre inmediato
		if(candidate.getNodeChilds().get(0) != null) candidate.getNodeChilds().get(0).setParent(candidate.getParent());
		if(candidate.getNodeChilds().get(1) != null) candidate.getNodeChilds().get(1).setParent(candidate.getParent());
		
		// Hacemos un setParent para establecer el padre del subarbol que se cambia 
		candidate.setParent(node.getParent());
		candidate.setNodeChilds(node.getNodeChilds());
		
		if(candidate.getParent() != null) candidate.getParent().getNodeChilds().set(candidate.getParent().getNodeChilds().indexOf(node), candidate);
		else this.setRoot(candidate);
	}
}
