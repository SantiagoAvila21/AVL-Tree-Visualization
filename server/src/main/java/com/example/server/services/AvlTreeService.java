package com.example.server.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.models.AvlTree;
import com.example.server.repository.AvlTreeRepository;

@Service
public class AvlTreeService {
    @Autowired
    private AvlTreeRepository repo;

    public JSONObject addNode(int value){
        return repo.addNewNode(value);
    }

    public JSONObject deleteNode(int value){
        return repo.delNode(value);
    }

    public JSONObject clearTree(){
        return repo.clearTree();
    }
}
