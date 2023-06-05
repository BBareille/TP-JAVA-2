package com.tpjava.tpjava2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Former {
    @Id
    private Long id;
    private String firstName;
    private String lastName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
