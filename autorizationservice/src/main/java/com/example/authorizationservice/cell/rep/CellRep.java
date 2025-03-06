package com.example.authorizationservice.cell.rep;


import com.example.authorizationservice.cell.domain.Cell;
import com.example.authorizationservice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRep extends JpaRepository<Cell, Long> {

    Cell findByCellId(Long cellId);


}
