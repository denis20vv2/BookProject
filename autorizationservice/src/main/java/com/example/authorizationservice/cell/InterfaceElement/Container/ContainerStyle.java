package com.example.authorizationservice.cell.InterfaceElement.Container;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContainerStyle {

    @NotNull(message = "padding is null")
    private String padding;

    @NotNull(message = "backgroundColor is null")
    private String backgroundColor;

    @NotNull(message = "borderRadius is null")
    private String borderRadius;

    @NotNull(message = "boxShadow is null")
    private String boxShadow;


    @Override
    public String toString() {
        return "ContainerStyle{" +
                "padding='" + padding + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", borderRadius='" + borderRadius + '\'' +
                ", boxShadow='" + boxShadow + '\'' +
                '}';
    }

}
