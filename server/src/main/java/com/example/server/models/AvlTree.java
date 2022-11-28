package com.example.server.models;

import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.server.models.tree.binarysearch.AvlNode;
import com.example.server.models.tree.binarysearch.BinarySearchTree;
import com.example.server.models.trees.Node;

public class AvlTree <T extends Comparable> extends BinarySearchTree<T> {
	
	public void addNode(T dato) {
		AvlNode<T> newNode = new AvlNode<T>(dato);
		addNode(newNode);
	}
	
	public void addNode(Node<T> node) {
		childs.add(node);
		if(root != null) {
			root.addNode(node);
			balanceTree(node);
		}else root = node;
	}
	
	// Balanceo Propio del Arbol
	public void balanceTree(Node<T> node) {
		Node<T> PivotNode = this.checkBalance(node);
		//System.out.println("Que pasa chavales" + node + " -- " + node.getParent() + " -- " + PivotNode);
		if(PivotNode == null) return;
		int balance1 = ((AvlNode)PivotNode).getBalanceFactor(), balance2;
		boolean der = false;
		if(balance1 > 0) {
			der = true;
			balance2 = ((AvlNode)PivotNode.getNodeChilds().get(1)).getBalanceFactor();
		}else balance2 = ((AvlNode)PivotNode.getNodeChilds().get(0)).getBalanceFactor();
					
		//Aplicar rotaciones dependiendo del caso correspondiente
		if(der && balance2 > 0) simpleRotationLeft(PivotNode);
		else if(der && balance2 <= 0) {
			simpleRotationRight(PivotNode.getNodeChilds().get(1));
			simpleRotationLeft(PivotNode);
		}else if(!der && balance2 > 0) {
			simpleRotationLeft(PivotNode.getNodeChilds().get(0));
			simpleRotationRight(PivotNode);
		}else if(!der && balance2 <= 0) simpleRotationRight(PivotNode);
	}
	
	// Para eliminar el Nodo, lo eliminamos como BST, y luego balanceamos desde el parent del eliminado
	public void deleteNode(Node<T> node) {
		if(node == null) return;
		Node<T> parentDeleted = node;
		super.deleteNode(node);
		balanceTree(((parentDeleted == null) ? root : parentDeleted));
	}
	
	// Rotacion simple por la izquierda
	public void simpleRotationLeft(Node<T> pivot) {
		Node<T> rightChild = pivot.getNodeChilds().get(1);
		
		if(rightChild.getNodeChilds().get(0) != null) rightChild.getNodeChilds().get(0).setParent(pivot);
		pivot.getNodeChilds().set(1, rightChild.getNodeChilds().get(0));
		rightChild.getNodeChilds().set(0, pivot);
		rightChild.setParent(pivot.getParent());
		pivot.setParent(rightChild);
		
		if(rightChild.getParent() != null) {			
			if(rightChild.getParent().getNodeChilds().indexOf(pivot) == 0) rightChild.getParent().getNodeChilds().set(0, rightChild);
			else rightChild.getParent().getNodeChilds().set(1, rightChild);
		} else this.setRoot(rightChild);
	}
	
	// Rotacion simple por la derecha
	public void simpleRotationRight(Node<T> pivot) {
		Node<T> leftChild = pivot.getNodeChilds().get(0);
		
		if(leftChild.getNodeChilds().get(1) != null) leftChild.getNodeChilds().get(1).setParent(pivot);
		pivot.getNodeChilds().set(0, leftChild.getNodeChilds().get(1));
		leftChild.getNodeChilds().set(1, pivot);
		leftChild.setParent(pivot.getParent());
		pivot.setParent(leftChild);
		
		if(leftChild.getParent() != null) {			
			if(leftChild.getParent().getNodeChilds().indexOf(pivot) == 0) leftChild.getParent().getNodeChilds().set(0, leftChild);
			else leftChild.getParent().getNodeChilds().set(1, leftChild);
		} else this.setRoot(leftChild);
	}
	
	// Profundidad Maxima del subarbol
	public int maxDepth(Node<T> node) {
		if(node == null) return 0;
		else {
			int lDepth = maxDepth((AvlNode) node.getNodeChilds().get(0));
			int rDepth = maxDepth((AvlNode) node.getNodeChilds().get(1));
			return Math.max(lDepth, rDepth) + 1;
		}
	}
	
	// Setear los factores de balanceo para cada nodo recursivamente y devolver el primer nodo Bottom-Up con desbalance
	public Node<T> checkBalance(Node<T> node){
		((AvlNode)node).setBalanceFactor(maxDepth((Node)node.getNodeChilds().get(1)) - maxDepth((Node)node.getNodeChilds().get(0)));
		if(Math.abs(((AvlNode)node).getBalanceFactor()) == 2) return node;
		else if(node.getParent() == null) return null;
		else return checkBalance(node.getParent());
	}

	public JSONObject setupAvl(Node node){
		if(node == null) return null;
		LinkedHashMap<String,Object> map = new LinkedHashMap<String, Object>();

		JSONArray array = new JSONArray();
		if(node.getNodeChilds().get(1) != null){
			JSONObject innerChildsR = setupAvl((Node) node.getNodeChilds().get(1));
			if(innerChildsR != null) array.put(innerChildsR);
		}
		if(node.getNodeChilds().get(0) != null){
			JSONObject innerChildsL = setupAvl((Node) node.getNodeChilds().get(0));
			if(innerChildsL != null) array.put(innerChildsL);
		}

		map.put("name", node.getValue() + "");
		map.put("children", array);

		return new JSONObject(map);
	}
}