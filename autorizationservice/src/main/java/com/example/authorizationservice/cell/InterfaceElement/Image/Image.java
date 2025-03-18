package com.example.authorizationservice.cell.InterfaceElement.Image;

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
public class Image {

    @NotNull(message = "border is null")
    private String id;

    @NotNull(message = "src is null")
    private String src;

    @NotNull(message = "alt is null")
    private String alt;

    @NotNull(message = "style is null")
    @NestedValid
    private StyleImage style;

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", src='" + src + '\'' +
                ", alt='" + alt + '\'' +
                ", style='" + style + '\'' +
                '}';
    }

}
