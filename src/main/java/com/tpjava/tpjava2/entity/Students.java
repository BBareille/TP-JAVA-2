package com.tpjava.tpjava2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Students {
    String firstName;
    String lastName;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return firstName +" "+ lastName;
    }
}
