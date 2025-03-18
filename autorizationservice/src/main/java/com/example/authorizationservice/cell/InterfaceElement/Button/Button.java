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
public class Button {

    /*@NotNull(message = "type is null")
    private String type;*/

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "label is null")
    private String label;

    @NotNull(message = "imageUrl is null")
    private String imageUrl;

    @NotNull(message = "action is null")
    @NestedValid
    private Action action;

    @Override
    public String toString() {
        return "Button{" +
                ", label='" + label + '\'' +
                ", id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", action=" + action +
                '}';
    }
}
