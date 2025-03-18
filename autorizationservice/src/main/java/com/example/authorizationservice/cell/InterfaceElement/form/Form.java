package com.example.authorizationservice.cell.InterfaceElement.form;

import com.example.authorizationservice.cell.validationСheck.NestedValid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Form {

    @NotNull(message = "fields is null")
    @NestedValid
    private List<Field> fields = new ArrayList<>();

    @NotNull(message = "submit is null")
    @NestedValid
    private Submit submit;

    @Override
    public String toString() {
        return "Form{" +
                "fields=" + fields +
                ", submit=" + submit +
                '}';
    }

}
