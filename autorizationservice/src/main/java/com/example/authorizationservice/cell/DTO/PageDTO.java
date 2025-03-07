package com.example.authorizationservice.cell.DTO;

import com.example.authorizationservice.cell.domain.Data;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
public class PageDTO {

    @NotNull(message = "data не задан")
    @JdbcTypeCode(SqlTypes.JSON)
    private Data data;

}
