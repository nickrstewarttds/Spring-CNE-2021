package com.qa.cne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cne.persistence.domain.Coffee;
import com.qa.cne.persistence.repository.CoffeeRepository;
import com.qa.cne.service.exceptions.CoffeeNotFoundException;

@Service
public class CoffeeService {

    private CoffeeRepository repo;

    @Autowired
    public CoffeeService(CoffeeRepository repo) {
        super();
        this.repo = repo;
    }

    // methods

    public Coffee create(Coffee coffee) {
        Coffee created = this.repo.save(coffee);
        return created;
    }

    public Coffee readById(Long id) {
        Coffee thingReadFromDb = this.repo.findById(id).orElseThrow(CoffeeNotFoundException::new);
        return thingReadFromDb;
    }

    public List<Coffee> readAll() {
        List<Coffee> thingsReadFromDb = this.repo.findAll();
        return thingsReadFromDb;
    }

    public Coffee updateById(Long id, Coffee newThing) {

        // grabs the thing we want to change from the db
        Coffee oldThing = this.repo.findById(id).orElseThrow(CoffeeNotFoundException::new);

        // here's the object we want to plug in instead:
        oldThing.setCoffeeBeanType(newThing.getCoffeeBeanType());
        oldThing.setCountryOfOrigin(newThing.getCountryOfOrigin());
        oldThing.setTemperature(newThing.getTemperature());

        // saves the changed object to the db
        return this.repo.save(oldThing);
    }

    public boolean deleteById(Long id) {
        // tries to delete the object first
        this.repo.deleteById(id);

        // checks if that object we tried to delete still exists
        return !this.repo.existsById(id);

    }

    // find by country
    public Coffee findByCountryOfOrigin(String countryOfOrigin) {
        return this.repo.findCoffeeByCountryOfOrigin(countryOfOrigin);
    }

    // find by temperature
    public Coffee findByTemperature(int temperature) {
        return this.repo.findCoffeeByTemperature(temperature);
    }

}
