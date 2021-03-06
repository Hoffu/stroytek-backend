package com.backend.stroytek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserValidation {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
