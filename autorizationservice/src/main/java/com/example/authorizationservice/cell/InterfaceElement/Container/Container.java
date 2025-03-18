package com.example.authorizationservice.cell.InterfaceElement.Container;

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
public class Container {

    @NotNull(message = "id is null")
   private String id;

    @NotNull(message = "layout is null")
    private String layout;

    @NotNull(message = "direction is null")
    private String direction;

    @NotNull(message = "alignItems is null")
    private String alignItems;

    @NotNull(message = "justifyContent is null")
    private String justifyContent;

    @NotNull(message = "style is null")
    @NestedValid
    private ContainerStyle style;

 @Override
 public String toString() {
  return "Container{" +
          "id='" + id + '\'' +
          ", layout='" + layout + '\'' +
          ", direction='" + direction + '\'' +
          ", alignItems='" + alignItems + '\'' +
          ", justifyContent='" + justifyContent + '\'' +
          ", style=" + style +
          '}';
 }

}
