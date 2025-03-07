package com.example.authorizationservice.cell.web;

import com.example.authorizationservice.cell.DTO.PageDTO;
import com.example.authorizationservice.cell.domain.Page;
import com.example.authorizationservice.cell.service.PageService;
import com.example.authorizationservice.user.web.UserController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        logger.info("Получение страницы с id:" + pageId);
        return pageService.getPage(pageId);
    }

    @PostMapping("/createPage")
    @ResponseBody
    @Operation(
            summary = "Создание страницы",
            description = "Создание страницы"
    )
    public Page createPage(@RequestBody PageDTO pageDTO) {
        logger.info("Сохдание новой страницы:");
        return pageService.createpage(pageDTO);
    }

}
