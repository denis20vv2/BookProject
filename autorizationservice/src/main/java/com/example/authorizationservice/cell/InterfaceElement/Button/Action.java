package com.example.authorizationservice.cell.InterfaceElement.Button;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    @NotNull
    private String type;

    @NotNull
    private String url;

    @NotNull
    @NestedValid
    private Style style;

    @Override
    public String toString() {
        return "Action{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", style=" + style +
                '}';
    }

}
