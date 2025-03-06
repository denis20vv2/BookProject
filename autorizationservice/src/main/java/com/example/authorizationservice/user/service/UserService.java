package com.example.authorizationservice.user.service;

import com.example.authorizationservice.user.DTO.CreateUser;
import com.example.authorizationservice.user.domain.User;
import com.example.authorizationservice.user.rep.UserRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRep userRep;

    public User setRole(Long userId, String role){

        User user = userRep.findByUserId(userId);
        user.setRole(role);

        return userRep.save(user);
    }

    public User createUser(CreateUser createUser){

        if (userRep.findByUsername(createUser.getUsername()) != null) {
            throw new EntityNotFoundException("Ошибка: Пользователь с таким именем уже существует!");
        }

        User user = new User(createUser.getRole(), passwordEncoder.encode(createUser.getPassword()) , createUser.getUsername());

        return userRep.save(user);

    }

}
