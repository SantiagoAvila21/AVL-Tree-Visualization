package com.example.server.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.AvlTree;
import com.example.server.services.AvlTreeService;

@RestController
@RequestMapping("api/avl")
public class AvlTreeController {
    @Autowired
    private AvlTreeService service;

    @PutMapping(path = "/update/{num}")
    @ResponseBody
    public ResponseEntity<Object> putNode(@PathVariable(value = "num") int value){
        return new ResponseEntity<>(service.addNode(value).toMap(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{num}")
    @ResponseBody
    public ResponseEntity<Object> deleteNode(@PathVariable(value = "num") int value){
        return new ResponseEntity<>(service.deleteNode(value).toMap(), HttpStatus.OK);
    }

    @RequestMapping( "/hello" )
    public String echo() {
      return "Hello World!";
    }

}