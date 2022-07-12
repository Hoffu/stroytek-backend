package com.backend.stroytek.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private int object_id;
    @NonNull
    private String full_name;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    private String wishes;

    public Order(@NonNull int object_id, @NonNull String full_name, @NonNull String phone,
                 @NonNull String email, @NonNull String wishes) {
        this.object_id = object_id;
        this.full_name = full_name;
        this.phone = phone;
        this.email = email;
        this.wishes = wishes;
    }
}
