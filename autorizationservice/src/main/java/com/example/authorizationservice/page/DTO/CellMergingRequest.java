package com.example.authorizationservice.page.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CellMergingRequest {

    @NotNull
    private Long pageId;

    @NotNull
    private List<String> keys;

}
