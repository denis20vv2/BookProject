package com.example.authorizationservice.page.web;

import com.example.authorizationservice.page.DTO.CellMergingRequest;
import com.example.authorizationservice.page.DTO.PageDTO;
import com.example.authorizationservice.page.domain.Data;
import com.example.authorizationservice.page.domain.Page;
import com.example.authorizationservice.page.service.PageService;
import com.example.authorizationservice.user.web.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/page")
@Tag(name="page")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class PageController {

    private final PageService pageService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/getPage/{pageId}")
    @ResponseBody
    @Operation(
            summary = "Получение страницы",
            description = "Получение страницы"
    )
    public Page getPage(@PathVariable Long pageId) {
        logger.info("Запрос на получение страницы с id:" + pageId);
        return pageService.getPage(pageId);
    }

    @PostMapping("/createPage")
    @ResponseBody
    @Operation(
            summary = "Создание страницы",
            description = "Создание страницы"
    )
    public Page createPage(@Valid @RequestBody PageDTO pageDTO) {
        logger.info("Запрос на сохдание новой страницы:");
        return pageService.createPage(pageDTO);
    }

    @PutMapping("/mergingCell/{pageId}")
    @ResponseBody
    @Operation(
            summary = "Объединение ячеек",
            description = "Объединение ячеек"
    )
    public Page mergingCell(@RequestBody CellMergingRequest cellMergingRequest) {
        logger.info("Запрос на сохраненине страницы с объединными ячейками:");
        return pageService.mergingCell(cellMergingRequest);
    }

    @PutMapping("/ungroupingCell/{pageId}")
    @ResponseBody
    @Operation(
            summary = "разъединение ячеек",
            description = "разъединение ячеек"
    )
    public Page ungroupingCell(@RequestBody CellMergingRequest cellMergingRequest) {
        logger.info("Запрос на сохраненине страницы с разъединенными ячейками:");
        return pageService.ungroupingCell(cellMergingRequest);
    }

}
