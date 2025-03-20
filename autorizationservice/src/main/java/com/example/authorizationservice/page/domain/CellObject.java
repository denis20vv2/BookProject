package com.example.authorizationservice.page.domain;

import com.example.authorizationservice.cell.validationСheck.ValidData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
public class CellObject {

    @NotNull
    private String key;

    private  String ownKey;

    private  Object data;

}
