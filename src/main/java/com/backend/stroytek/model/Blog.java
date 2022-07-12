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
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String img;
    @NonNull
    private String title;
    @NonNull
    private String date;
    @NonNull
    private String description;

    public Blog(@NonNull String img, @NonNull String title, @NonNull String date, @NonNull String description) {
        this.img = img;
        this.title = title;
        this.date = date;
        this.description = description;
    }
}
