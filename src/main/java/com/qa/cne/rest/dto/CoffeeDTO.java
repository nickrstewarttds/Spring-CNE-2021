package com.qa.cne.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoffeeDTO {

    private Long id;
    private int temperature;
    private String coffeeBeanType;
    private String countryOfOrigin;

//    {
//        "id":1,
//        "temperature":-5,
//        "coffeeBeanType":"dark roast",
//        "countryOfOrigin":"nicaragua",
//    }

}
