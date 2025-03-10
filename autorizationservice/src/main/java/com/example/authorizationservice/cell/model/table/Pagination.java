package com.example.authorizationservice.cell.model.table;

import jakarta.validation.constraints.NotNull;

public class Pagination {

    @NotNull(message = "currentPage is null")
    private Long currentPage;

    @NotNull(message = "totalPages is null")
    private Long totalPages;

    @NotNull(message = "pageSize is null")
    private Long pageSize;

    @NotNull(message = "totalItems is null")
    private Long totalItems;

}
