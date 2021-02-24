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

import com.qa.cne.persistence.domain.Developer;
import com.qa.cne.rest.dto.DeveloperDTO;
import com.qa.cne.service.DeveloperService;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperService service;

    @Autowired
    public DeveloperController(DeveloperService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<DeveloperDTO> create(@RequestBody Developer developer) {
        DeveloperDTO createdObject = this.service.create(developer);
        return new ResponseEntity<>(createdObject, HttpStatus.CREATED); // 201
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<DeveloperDTO> readById(@PathVariable Long id) {
        DeveloperDTO returnedObject = this.service.readById(id);
        return ResponseEntity.ok(returnedObject);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<DeveloperDTO>> readAll() {
        return ResponseEntity.ok(this.service.readAll()); // 200
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DeveloperDTO> updateById(@PathVariable Long id, @RequestBody DeveloperDTO developerDto) {
        DeveloperDTO updatedObject = this.service.updateById(id, developerDto);
        return new ResponseEntity<>(updatedObject, HttpStatus.ACCEPTED); // 202
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeveloperDTO> deleteById(@PathVariable Long id) {
        if (this.service.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 503
        }
    }

}
