package com.example.authorizationservice.authorization.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(description = "Запрос на аутентификацию")
@AllArgsConstructor
public class SignInRequest {

    @Schema(description = "логин", example = "user")
    @NotNull
    private String username;

    @Schema(description = "Пароль", example = "123456")
    @NotNull
    private String password;
}
