package com.tpjava.tpjava2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
