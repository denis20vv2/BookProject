package com.example.authorizationservice.table.service;

import com.example.authorizationservice.cell.InterfaceElement.dropdown.Dropdown;
import com.example.authorizationservice.cell.InterfaceElement.textBlock.TextBlock;
import com.example.authorizationservice.table.dto.TableDTO;
import com.example.authorizationservice.table.rep.TableRep;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.authorizationservice.table.domain.Table;
import org.webjars.NotFoundException;

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

    public Table saveTable(TableDTO tableDTO) {

        Table table = new Table(tableDTO.getColumns(), tableDTO.getData(), tableDTO.getFilterGroup());

        return tableRep.save(table);
    }



    public Table saveElement(Object object) {

        String receivedId;
        String targetId;

        Object targetObject;

        if (object instanceof TextBlock) {
            targetObject = (TextBlock) object;
            receivedId = ((TextBlock) targetObject).getId();
        } else if (object instanceof Dropdown) {
            targetObject = (Dropdown) object;
            receivedId = ((Dropdown) targetObject).getId();
        } else {
            throw new IllegalArgumentException("Неверная структура получаемого объекта");
        }

        Table table = getTable(1L);
        List<Map<String, Object>> data = table.getData();

        for (Map<String, Object> map : data) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();

                if (value instanceof TextBlock) {
                    TextBlock textBlock = (TextBlock) value;
                    targetId = textBlock.getId();
                } else if (value instanceof Dropdown) {
                    Dropdown dropdown = (Dropdown) value;
                    targetId = dropdown.getId();
                } else {
                    throw new IllegalArgumentException("Неверная структура объекта в мапе, не удалось определить динамический объект");
                }



                if (Objects.equals(targetId, receivedId)) {

                   // Object newObject = createNewObject(value);

                    entry.setValue(object);
                    System.out.println("Объект с id " + receivedId + " был заменён.");
                    return tableRep.save(table);
                }

            }

        }

        table.getColumns().size();
        Map<String, Object> lastMap = data.get(data.size() - 1);
        if(lastMap.size() == table.getColumns().size()){
            Map<String, Object> newRow = new HashMap<>();
            newRow.put("col1", object);
            data.add(newRow);
        }else{
            lastMap.put("col2", object);
        }

        // продумать логику сохранения новых объектов в конец мапы

        return table;
    }

    public Table getTable(Long id){

        return tableRep.findById(id)
                .orElseThrow(() -> new NotFoundException("Таблица с id " + id + " не найдена"));
    }


}
