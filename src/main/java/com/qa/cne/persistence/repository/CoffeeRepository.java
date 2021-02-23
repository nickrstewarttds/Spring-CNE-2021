package com.qa.cne.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.cne.persistence.domain.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    // SELECT * FROM Coffee WHERE country_of_origin = `?`;
    Coffee findCoffeeByCountryOfOrigin(String countryOfOrigin);

    @Query("SELECT c FROM Coffee c WHERE c.temperature = ?1")
    Coffee findCoffeeByTemperature(int temperature);

}
