package com.qa.cne.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cne.persistence.domain.Coffee;
import com.qa.cne.persistence.repository.CoffeeRepository;
import com.qa.cne.rest.dto.CoffeeDTO;
import com.qa.cne.service.exceptions.CoffeeNotFoundException;

@Service
public class CoffeeService {

    private CoffeeRepository repo;
    private ModelMapper mapper;

    @Autowired
    public CoffeeService(CoffeeRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    public CoffeeDTO create(Coffee coffee) {
        Coffee created = this.repo.save(coffee);
        CoffeeDTO converted = this.mapper.map(created, CoffeeDTO.class);
        return converted;
    }

    public CoffeeDTO readById(Long id) {
        Coffee thingReadFromDb = this.repo.findById(id).orElseThrow(CoffeeNotFoundException::new);
        CoffeeDTO converted = this.mapper.map(thingReadFromDb, CoffeeDTO.class);
        return converted;
    }

    public List<CoffeeDTO> readAll() {
        List<Coffee> thingsReadFromDb = this.repo.findAll();
        List<CoffeeDTO> convertedList = thingsReadFromDb.stream()
                .map(x -> this.mapper.map(Coffee.class, CoffeeDTO.class)).collect(Collectors.toList());
        return convertedList;
    }

    public CoffeeDTO updateById(Long id, CoffeeDTO newThing) {

        // grabs the thing we want to change from the db
        Coffee oldThing = this.repo.findById(id).orElseThrow(CoffeeNotFoundException::new);

        // here's the object we want to plug in instead:
        oldThing.setCoffeeBeanType(newThing.getCoffeeBeanType());
        oldThing.setCountryOfOrigin(newThing.getCountryOfOrigin());
        oldThing.setTemperature(newThing.getTemperature());

        // saves the changed object to the db
        Coffee thingToReturn = this.repo.save(oldThing);
        CoffeeDTO convertedThingToReturn = this.mapper.map(thingToReturn, CoffeeDTO.class);
        return convertedThingToReturn;
    }

    public boolean deleteById(Long id) {
        // tries to delete the object first
        this.repo.deleteById(id);

        // checks if that object we tried to delete still exists
        return !this.repo.existsById(id);

    }

    // find by country
    public CoffeeDTO findByCountryOfOrigin(String countryOfOrigin) {
        return this.mapper.map(this.repo.findCoffeeByCountryOfOrigin(countryOfOrigin), CoffeeDTO.class);
    }

    // find by temperature
    public CoffeeDTO findByTemperature(int temperature) {
        return this.mapper.map(this.repo.findCoffeeByTemperature(temperature), CoffeeDTO.class);
    }

}
