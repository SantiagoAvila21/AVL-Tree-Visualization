package com.example.server.repository;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.server.models.AvlTree;

@Repository
public class AvlTreeRepository{
    private AvlTree<Integer> Arbol = new AvlTree<Integer>();

    public JSONObject addNewNode(int value){
        Arbol.addNode(value);
        return Arbol.setupAvl(Arbol.getRoot());
    }

    public JSONObject delNode(int value){
        Arbol.deleteNode(Arbol.findNode(value));
        return Arbol.setupAvl(Arbol.getRoot());
    }

    public JSONObject clearTree(){
        Arbol = new AvlTree<Integer>();
        return Arbol.setupAvl(Arbol.getRoot());
    }
}