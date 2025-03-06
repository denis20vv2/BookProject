package com.example.authorizationservice.cell.service;

import com.example.authorizationservice.cell.DTO.CellDTO;
import com.example.authorizationservice.cell.domain.Cell;
import com.example.authorizationservice.cell.rep.CellRep;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CellService {

    private final CellRep cellRep;

    public Cell getCell(Long cellId){

        return cellRep.findByCellId(cellId);
    }

    public Cell createCell(CellDTO cellDTO){

        Cell cell = new Cell(cellDTO.getCellPositionX(), cellDTO.getCellPositionY(), cellDTO.getAppId(), cellDTO.getData());

        return cellRep.save(cell);
    }


}
