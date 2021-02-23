package com.qa.cne.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cne.persistence.domain.Coffee;
import com.qa.cne.service.CoffeeService;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    // passes things to the service.
    private CoffeeService service;

    @Autowired
    public CoffeeController(CoffeeService service) {
        super();
        this.service = service;
    }

    // create
    @PostMapping("/create")
    public ResponseEntity<Coffee> create(@RequestBody Coffee coffee) {
        Coffee createdObject = this.service.create(coffee);
        return new ResponseEntity<>(createdObject, HttpStatus.CREATED); // 201
    }

    // readById
    @GetMapping("/read/{id}")
    public ResponseEntity<Coffee> readById(@PathVariable Long id) {
        Coffee returnedObject = this.service.readById(id);
        return ResponseEntity.ok(returnedObject);
    }

    // readAll
    @GetMapping("/readAll")
    public ResponseEntity<List<Coffee>> readAll() {
        return ResponseEntity.ok(this.service.readAll()); // 200
    }

    // updateById
    @PutMapping("/update/{id}")
    public ResponseEntity<Coffee> updateById(@PathVariable Long id, @RequestBody Coffee coffee) {
        Coffee updatedObject = this.service.updateById(id, coffee);
        return new ResponseEntity<>(updatedObject, HttpStatus.ACCEPTED); // 202
    }

    // deleteById
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Coffee> deleteById(@PathVariable Long id) {
        if (this.service.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 503
        }
    }

    // 1xx -
    // 2xx - this is fine, something worked
    // 3xx -
    // 4xx - it's your fault, you've done something dodgy
    // 5xx - it's the developer's problem, they've messed up developing the
    // application

}
