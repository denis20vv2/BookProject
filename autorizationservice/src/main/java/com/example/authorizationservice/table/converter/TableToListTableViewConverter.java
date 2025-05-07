package com.example.authorizationservice.table.converter;

import com.example.authorizationservice.table.domain.Table;
import com.example.authorizationservice.table.view.TableView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TableToListTableViewConverter implements Converter<Table, TableView> {


    @Override
    public TableView convert(Table table) {

        TableView tableView = new TableView(table.getId(), table.getName());

        return tableView;
    }
}
