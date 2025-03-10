package com.example.authorizationservice.page.rep;


import com.example.authorizationservice.page.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRep extends JpaRepository<Page, Long> {

    Page findByPageId(Long cellId);


}
