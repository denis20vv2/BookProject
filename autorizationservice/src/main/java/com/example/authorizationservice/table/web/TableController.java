package com.example.authorizationservice.table.web;


import com.example.authorizationservice.table.domain.InterfaceElement;
import com.example.authorizationservice.table.dto.ComponentWrapper;
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
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.authorizationservice.table.domain.Table;
import com.example.authorizationservice.table.service.ComponentFactory;
import com.example.authorizationservice.table.service.ValidationService;


@RestController
@RequestMapping("/api/table")
@Tag(name="table")
@RequiredArgsConstructor
@Validated
public class TableController {

    private final ComponentFactory componentFactory;

    private final ValidationService validationService;
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
    public Table saveElement(@RequestBody @Valid ComponentWrapper wrapper) {
        logger.info("Запрос на создание нового элемента: {}", wrapper.getComponent());

        InterfaceElement parsed = componentFactory.parse(wrapper.getComponent(), wrapper.getData());

        validationService.validateComponent(parsed);

        return tableService.saveElement(parsed);
    }
}
