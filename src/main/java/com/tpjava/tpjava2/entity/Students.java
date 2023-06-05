package com.tpjava.tpjava2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Students {
    @Id
    private Long id;
    String firstName;
    String lastName;

}
