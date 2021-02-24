package com.qa.cne.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cne.persistence.domain.Developer;
import com.qa.cne.persistence.repository.DeveloperRepository;
import com.qa.cne.rest.dto.DeveloperDTO;
import com.qa.cne.service.exceptions.DeveloperNotFoundException;

@Service
public class DeveloperService {

    private DeveloperRepository repo;
    private ModelMapper mapper;

    @Autowired
    public DeveloperService(DeveloperRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    public DeveloperDTO create(Developer developer) {
        Developer created = this.repo.save(developer);
        DeveloperDTO converted = this.mapper.map(created, DeveloperDTO.class);
        return converted;
    }

    public DeveloperDTO readById(Long id) {
        Developer thingReadFromDb = this.repo.findById(id).orElseThrow(DeveloperNotFoundException::new);
        DeveloperDTO converted = this.mapper.map(thingReadFromDb, DeveloperDTO.class);
        return converted;
    }

    public List<DeveloperDTO> readAll() {
        List<Developer> thingsReadFromDb = this.repo.findAll();
        List<DeveloperDTO> convertedList = thingsReadFromDb.stream()
                .map(x -> this.mapper.map(Developer.class, DeveloperDTO.class)).collect(Collectors.toList());
        return convertedList;
    }

    public DeveloperDTO updateById(Long id, DeveloperDTO newThing) {

        // grabs the thing we want to change from the db
        Developer oldThing = this.repo.findById(id).orElseThrow(DeveloperNotFoundException::new);

        // here's the object we want to plug in instead:
        oldThing.setName(newThing.getName());
        oldThing.setJobTitle(newThing.getJobTitle());

        // saves the changed object to the db
        Developer thingToReturn = this.repo.save(oldThing);
        DeveloperDTO convertedThingToReturn = this.mapper.map(thingToReturn, DeveloperDTO.class);
        return convertedThingToReturn;
    }

    public boolean deleteById(Long id) {
        // tries to delete the object first
        this.repo.deleteById(id);

        // checks if that object we tried to delete still exists
        return !this.repo.existsById(id);

    }

}
