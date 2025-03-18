package com.example.authorizationservice.cell.InterfaceElement.Frame;

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
public class Frame {

    @NotNull(message = "id is null")
    private String id;

    @NotNull(message = "url is null")
    private String url;

    @NotNull(message = "sandbox is null")
    private boolean sandbox;

    @NotNull(message = "style is null")
    @NestedValid
    private StyleFrame style;

    @Override
    public String toString() {
        return "Frame{" +
                "id='" + id + '\'' +
                ", label='" + url + '\'' +
                ", checked='" + sandbox + '\'' +
                ", style=" + style +
                '}';
    }

}
