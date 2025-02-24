package com.example.authorizationservice.user.service;

import com.example.authorizationservice.user.domain.User;
import com.example.authorizationservice.user.rep.UserRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRep userRep;

    public User authorization(){

        return null;
    }

}
