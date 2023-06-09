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
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String firstName;
    String lastName;

    @ManyToMany(mappedBy = "studentsList")
    private List<Training> trainingList = new ArrayList<>();

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public boolean isValid(){
        return !(this.firstName == null|| this.firstName.isEmpty() || this.lastName == null || this.lastName.isEmpty());
    }

    @PreRemove
    private void removeStudentsTrainingAssociations()
    {
        for (Training training: this.trainingList)
        {
            training.getStudentsList().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
