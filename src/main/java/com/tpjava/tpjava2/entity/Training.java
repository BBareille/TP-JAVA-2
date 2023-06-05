package com.tpjava.tpjava2.entity;

import jakarta.persistence.*;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String start_at;
    private Integer duration;
    private Integer price;
    @ManyToOne( cascade = CascadeType.DETACH)
    @JoinColumn( name = "idCategory")
    private Category category;
    @ManyToOne( cascade = CascadeType.DETACH)
    @JoinColumn( name = "idLevel")
    private Level level;


    private boolean online;

    public Training() {
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_at='" + start_at + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", category=" + category +
                ", level=" + level +
                ", online=" + online +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
