package com.example.authorizationservice.cell.InterfaceElement.Image;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleImage {

    @NotNull(message = "width is null")
    private String width;

    @NotNull(message = "height is null")
    private String height;

    @NotNull(message = "borderRadius is null")
    private String borderRadius;

    @NotNull(message = "boxShadow is null")
    private String boxShadow;

    @Override
    public String toString() {
        return "StyleImage{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", borderRadius='" + borderRadius + '\'' +
                ", boxShadow='" + boxShadow + '\'' +
                '}';
    }

}
