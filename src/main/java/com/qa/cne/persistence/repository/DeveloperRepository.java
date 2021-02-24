package com.qa.cne.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.cne.persistence.domain.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
