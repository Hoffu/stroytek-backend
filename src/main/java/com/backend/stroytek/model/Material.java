package com.backend.stroytek.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String img;
    @NonNull
    private String text;

    public Material(@NonNull String name, @NonNull String img, @NonNull String text) {
        this.name = name;
        this.img = img;
        this.text = text;
    }
}
