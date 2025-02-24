package com.example.authorizationservice.authorization.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(description = "Запрос на регистрацию")
@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "Ivan")
    @NotNull
    private String username;

    @Schema(description = "Пароль", example = "123456")
    @NotNull
    private String password;

    //Какие поля нужны дополнительно для регистрации?
}
