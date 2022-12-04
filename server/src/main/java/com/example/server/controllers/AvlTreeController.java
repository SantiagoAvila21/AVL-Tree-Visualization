package com.example.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.services.AvlTreeService;

@RestController
@RequestMapping("api/avl")
public class AvlTreeController {
    @Autowired
    private AvlTreeService service;

    @CrossOrigin
    @PostMapping(path = "/update/{num}")
    @ResponseBody
    public ResponseEntity<Object> putNode(@PathVariable(value = "num") int value){
        return new ResponseEntity<>(service.addNode(value).toMap(), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path = "/delete/{num}")
    @ResponseBody
    public ResponseEntity<Object> deleteNode(@PathVariable(value = "num") int value){
        return new ResponseEntity<>(service.deleteNode(value).toMap(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping( "/clear" )
    public ResponseEntity<Object> clearTree() {
        return new ResponseEntity<>(service.clearTree().toMap(), HttpStatus.OK);
    }

}