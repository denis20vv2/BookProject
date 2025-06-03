package com.example.authorizationservice.table.web;


//import com.example.authorizationservice.page.domain.Page;
import com.example.authorizationservice.table.dto.TableDTO;
import com.example.authorizationservice.table.service.TableService;
import com.example.authorizationservice.table.view.TableView;
import com.example.authorizationservice.user.web.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.authorizationservice.table.domain.Table;
import com.example.authorizationservice.table.service.ComponentFactory;
import com.example.authorizationservice.table.service.ValidationService;

import java.util.List;


@RestController
@RequestMapping("/api/table")
@Tag(name="table")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class TableController {

    private final ComponentFactory componentFactory;

    private final ValidationService validationService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final TableService tableService;

    @PutMapping("/changeTable")
    @ResponseBody
    @Operation(
            summary = "Изменение таблицы",
            description = "Изменение таблицы"
    )
    public Table changeTable(@Valid  @RequestBody Table table) {

        logger.info("Запрос на изменение таблицы:");
        return tableService.changeTable(table);
    }

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

    @PutMapping("/applyingFilters")
    @ResponseBody
    @Operation(
            summary = "Применение фильтров",
            description = "Применение фильтров"
    )
    public Table applyingFilters(@Valid  @RequestBody Table table) {

        logger.info("Запрос на применение фильтров:");
        return tableService.applyingFilters(table);
    }

    /*@PostMapping("/saveElement")
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
    }*/

    @GetMapping("/getTable/{id}")
    @ResponseBody
    @Operation(
            summary = "Получение таблицы по id",
            description = "Получение таблицы по id"
    )
    public Table getTable(@PathVariable Long id) {
        logger.info("Запрос на получение таблицы с id:" + id);
        return tableService.getTableById(id);
    }

    @GetMapping("/getAllTables")
    @ResponseBody
    @Operation(
            summary = "Запрос на получение списка таблиц",
            description = "Запрос на получение списка таблиц"
    )
    public List<TableView> getAllTables(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ) {
        logger.info("Запрос на получение списка таблиц:");
        return tableService.getAllTables(page, size);
    }

}
