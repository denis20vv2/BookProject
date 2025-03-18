package com.example.authorizationservice.cell.InterfaceElement.dropdown;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleDropdown {

    @NotNull(message = "width is null")
    private String width;

    @NotNull(message = "borderRadius is null")
    private String borderRadius;

    @NotNull(message = "backgroundColor is null")
    private String backgroundColor;

    @NotNull(message = "borderColor is null")
    private String borderColor;

    @Override
    public String toString() {
        return "StyleDropdown{" +
                "width='" + width + '\'' +
                ", borderRadius='" + borderRadius + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", borderColor='" + borderColor + '\'' +
                '}';
    }

}
