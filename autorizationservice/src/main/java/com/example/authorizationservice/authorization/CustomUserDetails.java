package com.example.authorizationservice.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    // Конструктор
    public CustomUserDetails(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.authorities = List.of(new SimpleGrantedAuthority(role)); // Роль пользователя
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Предполагаем, что аккаунт не истек
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Предполагаем, что аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Предполагаем, что учетные данные не истекли
    }

    @Override
    public boolean isEnabled() {
        return true;  // Предполагаем, что аккаунт активен
    }
}
