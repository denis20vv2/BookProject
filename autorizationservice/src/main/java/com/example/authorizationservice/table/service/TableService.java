package com.example.authorizationservice.table.service;

import com.example.authorizationservice.cell.InterfaceElement.checkbox.Checkbox;
import com.example.authorizationservice.cell.InterfaceElement.dropdown.Dropdown;
import com.example.authorizationservice.cell.InterfaceElement.textBlock.TextBlock;
import com.example.authorizationservice.table.converter.TableToListTableViewConverter;
import com.example.authorizationservice.table.domain.InterfaceElement;
import com.example.authorizationservice.table.dto.TableDTO;
import com.example.authorizationservice.table.rep.TableRep;
import com.example.authorizationservice.table.view.TableView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.authorizationservice.table.domain.Table;
import org.webjars.NotFoundException;
import org.springframework.data.domain.Page;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@AllArgsConstructor
@Service
@NoArgsConstructor
public class TableService {

    @Autowired
    private TableRep tableRep;

    @Autowired
    private TableToListTableViewConverter tableToListTableViewConverter;

    @Autowired
    private ObjectMapper objectMapper;

    public Table saveTable(TableDTO tableDTO) {

        Table table = new Table(tableDTO.getName(), tableDTO.getColumns(), tableDTO.getData(), tableDTO.getAvailableFilters(), tableDTO.getAppliedFilters() );

        return tableRep.save(table);
    }



    public Table saveElement(InterfaceElement newComponent) {
        String receivedId = newComponent.getId();

        Table table = getTable(1L);
        List<Map<String, Object>> data = table.getData();

        for (Map<String, Object> row : data) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                Object existing = entry.getValue();



                if (existing instanceof Map) {
                    Map<String, Object> rawComponent = (Map<String, Object>) existing;
                    String type = (String) rawComponent.get("type");


                    InterfaceElement typedComponent = convertToComponent(type, rawComponent);
                    entry.setValue(typedComponent);

                    existing = typedComponent;
                }



                if (!(existing instanceof InterfaceElement)) {
                    throw new IllegalArgumentException("Неизвестный компонент в таблице");
                }

                String existingId = ((InterfaceElement) existing).getId();

                if (Objects.equals(receivedId, existingId)) {
                    entry.setValue(newComponent);
                    System.out.println("Объект с id " + receivedId + " был заменён.");
                    return tableRep.save(table);
                }
            }
        }

        // Добавить как новый компонент, если не найден
        Map<String, Object> lastRow = data.get(data.size() - 1);
        int colSize = table.getColumns().size();

        if (lastRow.size() >= colSize) {
            Map<String, Object> newRow = new HashMap<>();
            newRow.put("col1", newComponent);
            data.add(newRow);
        } else {
            String newCol = "col" + (lastRow.size() + 1);
            lastRow.put(newCol, newComponent);
        }

        return tableRep.save(table);
    }

    private InterfaceElement convertToComponent(String type, Map<String, Object> raw) {
        switch (type) {
            case "INPUT":
                return objectMapper.convertValue(raw, TextBlock.class);
            case "DROPDOWN":
                return objectMapper.convertValue(raw, Dropdown.class);
            case "CHECKBOX":
                return objectMapper.convertValue(raw, Checkbox.class);
            default:
                throw new IllegalArgumentException("Неизвестный тип компонента: " + type);
        }
    }


    public Table getTable(Long id){

        return tableRep.findById(id)
                .orElseThrow(() -> new NotFoundException("Таблица с id " + id + " не найдена"));
    }


    public List<TableView> getAllTables(int page, int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return tableRep.findAll(pageable)
                .map(tableToListTableViewConverter::convert)
                .toList();

       /* return tableRep.findById(id)
                .orElseThrow(() -> new NotFoundException("Таблица с id " + id + " не найдена"));*/
    }

}
