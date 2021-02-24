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
import com.qa.cne.rest.dto.CoffeeDTO;
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
    public ResponseEntity<CoffeeDTO> create(@RequestBody Coffee coffee) {
        CoffeeDTO createdObject = this.service.create(coffee);
        return new ResponseEntity<>(createdObject, HttpStatus.CREATED); // 201
    }

    // readById
    @GetMapping("/read/{id}")
    public ResponseEntity<CoffeeDTO> readById(@PathVariable Long id) {
        CoffeeDTO returnedObject = this.service.readById(id);
        return ResponseEntity.ok(returnedObject);
    }

    // findByCountry
    @GetMapping("/read/byCountry/{countryOfOrigin}")
    public ResponseEntity<CoffeeDTO> findByCountryOfOrigin(@PathVariable String countryOfOrigin) {
        CoffeeDTO found = this.service.findByCountryOfOrigin(countryOfOrigin);
        return ResponseEntity.ok(found);
    }

    // findByTemperature
    @GetMapping("/read/byTemperature/{temperature}")
    public ResponseEntity<CoffeeDTO> findByTemperature(@PathVariable int temperature) {
        CoffeeDTO found = this.service.findByTemperature(temperature);
        return ResponseEntity.ok(found);
    }

    // readAll
    @GetMapping("/readAll")
    public ResponseEntity<List<CoffeeDTO>> readAll() {
        return ResponseEntity.ok(this.service.readAll()); // 200
    }

    // updateById
    @PutMapping("/update/{id}")
    public ResponseEntity<CoffeeDTO> updateById(@PathVariable Long id, @RequestBody CoffeeDTO coffeeDto) {
        CoffeeDTO updatedObject = this.service.updateById(id, coffeeDto);
        return new ResponseEntity<>(updatedObject, HttpStatus.ACCEPTED); // 202
    }

    // deleteById
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CoffeeDTO> deleteById(@PathVariable Long id) {
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
