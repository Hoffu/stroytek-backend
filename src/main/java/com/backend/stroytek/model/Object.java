package com.backend.stroytek.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String price;
    @NonNull
    private String img;
    @NonNull
    private String type;
    @NonNull
    private int floors;
    @NonNull
    private double square;
    @NonNull
    private String walls;
    @NonNull
    private String description;
    @NonNull
    private String foundation;
    @NonNull
    private String frame;
    @NonNull
    private String roof;
    @NonNull
    private String windows;
    @NonNull
    private String doors;

    public Object(@NonNull String name, @NonNull String price, @NonNull String img, @NonNull String type,
                  @NonNull int floors, @NonNull double square, @NonNull String walls, @NonNull String description,
                  @NonNull String foundation, @NonNull String frame, @NonNull String roof, @NonNull String windows,
                  @NonNull String doors) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.type = type;
        this.floors = floors;
        this.square = square;
        this.walls = walls;
        this.description = description;
        this.foundation = foundation;
        this.frame = frame;
        this.roof = roof;
        this.windows = windows;
        this.doors = doors;
    }
}
