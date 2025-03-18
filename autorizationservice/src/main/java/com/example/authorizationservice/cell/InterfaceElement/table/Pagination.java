package com.example.authorizationservice.cell.InterfaceElement.table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

    @NotNull(message = "currentPage is null")
    private Long currentPage;

    @NotNull(message = "totalPages is null")
    private Long totalPages;

    @NotNull(message = "pageSize is null")
    private Long pageSize;

    @NotNull(message = "totalItems is null")
    private Long totalItems;

    @Override
    public String toString() {
        return "Pagination{" +
                "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", pageSize=" + pageSize +
                ", totalItems=" + totalItems +
                '}';
    }

}
