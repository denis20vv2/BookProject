package com.example.authorizationservice.cell.web;

import com.example.authorizationservice.cell.DTO.CellDTO;
import com.example.authorizationservice.cell.domain.Cell;
import com.example.authorizationservice.cell.service.CellService;
import com.example.authorizationservice.user.DTO.CreateUser;
import com.example.authorizationservice.user.domain.User;
import com.example.authorizationservice.user.service.UserService;
import com.example.authorizationservice.user.web.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cell")
@Tag(name="cell")
@RequiredArgsConstructor
@Validated
public class CellController {

    private final CellService cellService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getCell/{cellId}")
    @ResponseBody
    @Operation(
            summary = "Получение ячеек таблицы",
            description = "Получение ячеек таблицы"
    )
    public Cell authorization(@PathVariable Long cellId) {
        logger.info("Получение ячейки с id:" + cellId);
        return cellService.getCell(cellId);
    }

    @PostMapping("/createCell")
    @ResponseBody
    @Operation(
            summary = "Создание ячеек таблицы",
            description = "Создание ячеек таблицы"
    )
    public Cell createCell(@RequestBody CellDTO cellDTO) {
        logger.info("Сохдание новой ячейки:");
        return cellService.createCell(cellDTO);
    }

}
