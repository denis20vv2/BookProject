package com.example.authorizationservice.cell.InterfaceElement.link;

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
public class Link {

    @NotNull(message = "text is null")
    private String text;

    @NotNull(message = "url is null")
    private String url;

    @NotNull(message = "dynamicParams is null")
    private String dynamicParams;

    @NotNull(message = "target is null")
    private String target;

    @NotNull(message = "icon is null")
    @NestedValid
    private Icon icon;

    @NotNull(message = "style is null")
    @NestedValid
    private StyleLink style;

    @Override
    public String toString() {
        return "Link{" +
                "text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", dynamicParams='" + dynamicParams + '\'' +
                ", target='" + target + '\'' +
                ", icon=" + icon +
                ", styles=" + style +
                '}';
    }

}
