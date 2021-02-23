package com.qa.cne.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.cne.persistence.domain.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
