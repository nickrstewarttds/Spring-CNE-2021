package com.qa.cne.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    // variables

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Min(-5)
    @Max(95)
    @Column
    private int temperature;

    @Column
    private String coffeeBeanType; // coffee_bean_type

    @Column
    private String countryOfOrigin;

    @ManyToOne
    private Developer developer;

    // no args - needed when we're returning a List<Coffee> (like readAll() )
//    public Coffee() {
//
//    }

    // all args - needed for testing
//    public Coffee(Long id, @Min(-5) @Max(95) int temperature, String coffeeBeanType, String countryOfOrigin) {
//        super();
//        this.id = id;
//        this.temperature = temperature;
//        this.coffeeBeanType = coffeeBeanType;
//        this.countryOfOrigin = countryOfOrigin;
//    }

    // what if we're generating that id?
    public Coffee(@Min(-5) @Max(95) int temperature, String coffeeBeanType, String countryOfOrigin) {
        super();
        this.temperature = temperature;
        this.coffeeBeanType = coffeeBeanType;
        this.countryOfOrigin = countryOfOrigin;
    }
}
