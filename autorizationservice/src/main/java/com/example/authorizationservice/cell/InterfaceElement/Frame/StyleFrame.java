package com.example.authorizationservice.cell.InterfaceElement.Frame;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleFrame {

    @NotNull(message = "width is null")
    private String width;

    @NotNull(message = "height is null")
    private String height;

    @NotNull(message = "border is null")
    private String border;

    @Override
    public String toString() {
        return "StyleFrame{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", border='" + border + '\'' +
                '}';
    }

}
