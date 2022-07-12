package com.backend.stroytek.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;

    public User(@NonNull String phone, @NonNull String email, @NonNull String password, @NonNull String name) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
