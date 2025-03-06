package com.example.authorizationservice.cell.DTO;

import com.example.authorizationservice.cell.domain.Data;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
public class CellDTO {

    @NotNull(message = "cellPositionX не задан")
    private Long cellPositionX;
    @NotNull(message = "cellPositionY не задан")
    private Long cellPositionY;

    @NotNull(message = "appId не задан")
    private Long appId;

    @NotNull(message = "data не задан")
    @JdbcTypeCode(SqlTypes.JSON)
    private Data data;

}
