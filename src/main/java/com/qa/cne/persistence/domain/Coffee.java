package com.qa.cne.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Coffee {

    // variables

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Min(-5)
    @Max(95)
    @Column
    private int temperature;

    @Column
    private String coffeeBeanType; // coffee_bean_type

    @Column
    private String countryOfOrigin;

    // constructors
    public Coffee(int temperature, String coffeeBeanType, String countryOfOrigin) {
        super();
        this.temperature = temperature;
        this.coffeeBeanType = coffeeBeanType;
        this.countryOfOrigin = countryOfOrigin;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getCoffeeBeanType() {
        return coffeeBeanType;
    }

    public void setCoffeeBeanType(String coffeeBeanType) {
        this.coffeeBeanType = coffeeBeanType;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    // toString
    @Override
    public String toString() {
        return String.format("Coffee [id=%s, temperature=%s, coffeeBeanType=%s, countryOfOrigin=%s]", id, temperature,
                coffeeBeanType, countryOfOrigin);
    }

}
