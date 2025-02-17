package com.example.authorizationservice.user.web;

import com.example.authorizationservice.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
@Tag(name="user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("authorization")
    @ResponseBody
    @Operation(
            summary = "авторизация сотрудника",
            description = "авторизация сотрудника"
    )
    public User authorization() {
        logger.info("Авторизация сотрудника");
        return userService.authorization();
    }

}
