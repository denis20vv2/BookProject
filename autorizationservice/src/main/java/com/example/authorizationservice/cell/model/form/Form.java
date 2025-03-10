package com.example.authorizationservice.cell.model.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Form {

    @NotNull(message = "fields is null")
    private List<Field> fields = new ArrayList<>();
    @NotNull(message = "submit is null")
    private Submit submit;


}
