package com.example.authorizationservice.cell.model.link;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Icon {

    @NotNull(message = "type is null")
    private String type;

    @NotNull(message = "src is null")
    private String src;

    @NotNull(message = "alt is null")
    private String alt;

}
