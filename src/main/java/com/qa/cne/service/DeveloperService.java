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
import com.qa.cne.utils.MappingUtils;

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

    private DeveloperDTO mapToDto(Developer developer) {
        return this.mapper.map(developer, DeveloperDTO.class);
    }

    public DeveloperDTO create(Developer developer) {
        Developer created = this.repo.save(developer);
        DeveloperDTO converted = this.mapToDto(created);
        return converted;
    }

    public DeveloperDTO readById(Long id) {
        Developer thingReadFromDb = this.repo.findById(id).orElseThrow(DeveloperNotFoundException::new);
        DeveloperDTO converted = this.mapToDto(thingReadFromDb);
        return converted;
    }

    public List<DeveloperDTO> readAll() {
        List<Developer> thingsReadFromDb = this.repo.findAll();
        List<DeveloperDTO> convertedList = thingsReadFromDb.stream().map(this::mapToDto).collect(Collectors.toList());
        return convertedList;
    }

    public DeveloperDTO updateById(Long id, DeveloperDTO developerDTO) {
        Developer toUpdate = this.repo.findById(id).orElseThrow(DeveloperNotFoundException::new);
        toUpdate.setName(developerDTO.getName());
        toUpdate.setJobTitle(developerDTO.getJobTitle());
        MappingUtils.mergeNotNull(developerDTO, toUpdate);
        return this.mapToDto(this.repo.save(toUpdate));
    }

    public boolean deleteById(Long id) {
        // tries to delete the object first
        this.repo.deleteById(id);

        // checks if that object we tried to delete still exists
        return !this.repo.existsById(id);

    }

}
