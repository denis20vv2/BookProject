package com.example.authorizationservice.application.rep;

import com.example.authorizationservice.application.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRep extends JpaRepository<App, Long> {



}
