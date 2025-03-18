package com.example.authorizationservice.page.domain;

import com.example.authorizationservice.cell.validationСheck.ValidData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {

    //@ValidData
    private List<CellObject> cellObjects;

    //private Object data;

}
