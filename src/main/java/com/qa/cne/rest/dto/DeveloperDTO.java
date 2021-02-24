package com.qa.cne.rest.dto;

import java.util.List;

import com.qa.cne.persistence.domain.Coffee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeveloperDTO {

    // data transfer object
    // represents the structure of our data in a nice readable format to the
    // end-user

    private Long id;
    private String name;
    private String jobTitle;
    private List<Coffee> coffee;

//    {
//        "id":1,
//        "name":"Nick",
//        "jobTitle":"trainer",
//        "coffee": [
//            {
//                "id": 1
//            },
//            {
//                "id": 2
//            }
//        ]
//    }

}
