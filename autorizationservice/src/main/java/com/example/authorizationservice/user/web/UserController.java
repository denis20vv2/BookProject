package com.example.authorizationservice.user.web;

import com.example.authorizationservice.authorization.DTO.SignInRequest;
import com.example.authorizationservice.authorization.DTO.SignUpRequest;
import com.example.authorizationservice.authorization.JwtResponse;
import com.example.authorizationservice.authorization.config.JwtTokenProvider;
import com.example.authorizationservice.user.DTO.CreateUser;
import com.example.authorizationservice.user.domain.User;
import com.example.authorizationservice.user.rep.UserRep;
import com.example.authorizationservice.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Tag(name="user")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRep userRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/setRole/{userId}")
    @ResponseBody
    @Operation(
            summary = "Изменение роли",
            description = "Изменение роли"
    )
    public User authorization(@PathVariable Long userId, String role) {
        logger.info("Изменение роли сотруднику с id:" + userId);
        return userService.setRole(userId, role);
    }

    @PostMapping("/createUser")
    @Operation(
            summary = "Создание пользоваетля",
            description = "Создание пользоваетля"
    )
    public User CreateUser(@RequestBody CreateUser createUser) {
        logger.info("Создание нового пользователя:" + createUser.getUsername());
        //String encodedPassword = passwordEncoder.encode(createUser.getPassword());
        return userService.createUser(createUser);
    }

    //Убрать в сервисный слой в дальнейшем логику метода /login
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody SignInRequest signInRequest) {
        try {
            //logger.info("debug");
            Authentication authentication = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(

                            signInRequest.getUsername(),
                            signInRequest.getPassword()
                    )
            );
            //logger.info("debug");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.createToken(authentication);

            com.example.authorizationservice.user.domain.User user = userRep.findByUsername(signInRequest.getUsername());
            Map<String, Object> response = null;

            //logger.info("debug");

            if (user != null) {
                response = Map.of("user_id", user.getUserId(), "role", user.getRole());
            }

            //logger.info("debug");

            return ResponseEntity.ok(List.of(new JwtResponse(jwt), user));
        } catch (AuthenticationException e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверное имя пользователя или пароль");

        }
    }

   /* @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRep.findByUsername(signUpRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Ошибка: Пользователь с таким именем уже существует!");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        com.example.authorizationservice.user.domain.User newUser = new com.example.authorizationservice.user.domain.User();
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRole("ROLE_VIEWER");

        userRep.save(newUser);

        return ResponseEntity.ok("Пользователь успешно зарегистрирован!");

    }*/

}
