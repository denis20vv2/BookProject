package com.example.authorizationservice.table.web;


import com.example.authorizationservice.table.dto.TableDTO;
import com.example.authorizationservice.table.service.TableService;
import com.example.authorizationservice.user.web.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.authorizationservice.table.domain.Table;


@RestController
@RequestMapping("/api/table")
@Tag(name="table")
@RequiredArgsConstructor
@Validated
public class TableController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final TableService tableService;

    @PostMapping("/saveTable")
    @ResponseBody
    @Operation(
            summary = "Создание таблицы",
            description = "Создание таблицы"
    )
    public Table saveTable(@Valid  @RequestBody TableDTO tableDTO) {

        logger.info("Запрос на сохдание новой таблицы:");
        return tableService.saveTable(tableDTO);
    }

    @PostMapping("/saveElement")
    @ResponseBody
    @Operation(
            summary = "Создание таблицы",
            description = "Создание таблицы"
    )
    public Table saveElement( @RequestBody Object object) {

        logger.info("Запрос на сохдание нового элемента:");
        return tableService.saveElement(object);
    }
}
