package com.tpjava.tpjava2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @ManyToMany( cascade = CascadeType.DETACH, targetEntity = Students.class)
    @JoinTable(
            name = "training_to_students",
            joinColumns = @JoinColumn ( name = "idTraining"),
            inverseJoinColumns = @JoinColumn ( name = "idStudents")
    )
    private List<Students> studentsList = new ArrayList<>();

    @ManyToMany( cascade = CascadeType.DETACH , targetEntity = Former.class)
    @JoinTable(
            name = "training_to_former",
            joinColumns = @JoinColumn ( name = "idTraining"),
            inverseJoinColumns = @JoinColumn ( name = "idFormer")
    )
    private List<Former> formerList = new ArrayList<>();

    public boolean isValid(){
        return !(this.category == null || this.level == null || this.name == null || this.name.isEmpty() || this.price == null || this.startAt == null || this.startAt.isEmpty());
    }

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

//    public String getStartAt(){
//        if(startAt == null) return "";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);
//        LocalDate date = LocalDate.parse(startAt, formatter);
//        DateTimeFormatter sformatter = DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.FRANCE);
//        return sformatter.format(date);
//    }
}
