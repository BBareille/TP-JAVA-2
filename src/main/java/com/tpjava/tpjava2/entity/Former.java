package com.tpjava.tpjava2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Former {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Training.class)
    private List<Training> trainingList = new ArrayList<>();

    public boolean isValid(){
        return !(this.firstName == null || this.lastName == null);
    }
}
