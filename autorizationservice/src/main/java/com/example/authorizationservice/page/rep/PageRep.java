package com.example.authorizationservice.page.rep;


import com.example.authorizationservice.page.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRep extends JpaRepository<Page, Long> {

    Page findByPageId(Long cellId);

   /* @Query(value = "SELECT p.data FROM page p WHERE p.page_id = :pageId", nativeQuery = true)
    String getPageDataByPageId(@Param("pageId") Long pageId);*/


}
