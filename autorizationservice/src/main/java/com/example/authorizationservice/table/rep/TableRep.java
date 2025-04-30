package com.example.authorizationservice.table.rep;

import com.example.authorizationservice.page.domain.Page;
import com.example.authorizationservice.table.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRep extends JpaRepository<Table, Long>{



}
