package com.example.authorizationservice.user.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {

    @NotNull(message = "роль не задана")
    private String role;

    @NotNull(message = "парооь не задан")
    private String password;

    @NotNull(message = "логин не задан")
    private String username;


}
