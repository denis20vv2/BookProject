package com.example.authorizationservice.cell.InterfaceElement.dropdown;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import com.example.authorizationservice.table.domain.InterfaceElement;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dropdown implements InterfaceElement {

    @NotNull
    private String id;

    @NotNull
    private String value;

    @NotNull
    private String type;

    @NotNull
    private Boolean active;

    @NotNull
    private List<String> optionsList;

    @Override
    public String toString() {
        return "Dropdown{" +
                "id='" + id + '\'' +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", active='" + active + '\'' +
                ", optionsList=" + optionsList +
                '}';
    }

}
