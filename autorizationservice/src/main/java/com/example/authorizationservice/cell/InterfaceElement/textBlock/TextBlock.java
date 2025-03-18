package com.example.authorizationservice.cell.InterfaceElement.textBlock;

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
public class TextBlock {

    @NotNull(message = "type is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "type is null")
    private String type;

    @NotNull(message = "value is null")
    private String value;

    @NotNull(message = "styles is null")
    @NestedValid
    private StyleTextBlock styles;

    @Override
    public String toString() {
        return "TextBlock{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", value='" + type + '\'' +
                ", styles=" + styles +
                '}';
    }

}
