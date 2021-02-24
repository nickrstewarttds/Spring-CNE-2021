package com.qa.cne.persistence.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String jobTitle;

    @OneToMany(mappedBy = "developer")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Coffee> coffee;

    public Developer(String name, String jobTitle, List<Coffee> coffee) {
        super();
        this.name = name;
        this.jobTitle = jobTitle;
        this.coffee = coffee;
    }

}
