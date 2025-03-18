package com.example.authorizationservice.cell.InterfaceElement.filters;

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
public class ActionFilter {

    @NotNull(message = "apply is null")
    @NestedValid
    private ExistAction apply;

    @NotNull(message = "filters is null")
    @NestedValid
    private ExistAction reset;

    @Override
    public String toString() {
        return "ActionFilter{" +
                "apply=" + apply +
                ", reset=" + reset +
                '}';
    }

}
