package com.example.authorizationservice.cell.InterfaceElement.Button;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Style {

    @NotNull(message = "width is null")
    private String width;

    @NotNull(message = "height is null")
    private String height;

    @NotNull(message = "backgroundColor is null")
    private String backgroundColor;

    @NotNull(message = "textColor is null")
    private String textColor;

    @NotNull(message = "borderRadius is null")
    private String borderRadius;

    @Override
    public String toString() {
        return "Style{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", textColor='" + textColor + '\'' +
                ", borderRadius='" + borderRadius + '\'' +
                '}';
    }

}
