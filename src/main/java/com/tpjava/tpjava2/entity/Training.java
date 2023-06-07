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
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "start_at")
    private String startAt;
    private Integer duration;
    private Integer price;

    @ManyToOne( cascade = CascadeType.DETACH)
    @JoinColumn( name = "idCategory")
    private Category category;

    @ManyToOne( cascade = CascadeType.DETACH)
    @JoinColumn( name = "idLevel")
    private Level level;

    private boolean online;

    @ManyToMany( cascade = CascadeType.ALL, targetEntity = Students.class)
    @JoinTable(
            name = "training_to_students",
            joinColumns = @JoinColumn ( name = "idTraining"),
            inverseJoinColumns = @JoinColumn ( name = "idStudents")
    )
    private List<Students> studentsList = new ArrayList<>();

    @ManyToMany( cascade = CascadeType.ALL , targetEntity = Former.class)
    @JoinTable(
            name = "training_to_former",
            joinColumns = @JoinColumn ( name = "idTraining"),
            inverseJoinColumns = @JoinColumn ( name = "idFormer")
    )
    private List<Former> formerList = new ArrayList<>();


    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startAt='" + startAt + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", category=" + category +
                ", level=" + level +
                ", online=" + online +
                '}';
    }
}
