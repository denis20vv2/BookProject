package com.example.authorizationservice.cell.InterfaceElement.table;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableConverter {

    public static Table convertToTable(Object dataMap) {
        if (dataMap instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) dataMap;
            Table table = new Table();

            table.setSearch((String) map.get("search"));

            if (map.containsKey("filter") && map.get("filter") instanceof Map) {
                Map<String, Object> filterMap = (Map<String, Object>) map.get("filter");
                FilterTable filter = convertToFilter(filterMap);
                table.setFilter(filter);
            }

            if (map.containsKey("columns") && map.get("columns") instanceof List<?>) {
                List<?> rawColumnsList = (List<?>) map.get("columns");

                List<Column> columns = rawColumnsList.stream()
                        .filter(item -> item instanceof Map)
                        .map(item -> convertToColumn((Map<String, Object>) item))
                        .collect(Collectors.toList());

                table.setColumns(columns);
            }

            if (map.containsKey("rows") && map.get("rows") instanceof List<?>) {
                List<?> rawColumnsList = (List<?>) map.get("rows");

                List<Row> rows = rawColumnsList.stream()
                        .filter(item -> item instanceof Map) // Фильтруем, чтобы избежать ClassCastException
                        .map(item -> convertToRow((Map<String, Object>) item))
                        .collect(Collectors.toList());

                table.setRows(rows);
            }
            if (map.containsKey("pagination") && map.get("pagination") instanceof Map) {
                Map<String, Object> paginationMap = (Map<String, Object>) map.get("pagination");
                Pagination pagination = convertToPagination(paginationMap);
                table.setPagination(pagination);
            }

            return table;
        }
        throw new IllegalArgumentException("Invalid object type");
    }

    public static Column convertToColumn(Map<String, Object> columnMap) {
        Column column = new Column();
        column.setKey((String) columnMap.get("key"));
        column.setLabel((String) columnMap.get("label"));
        column.setType((String) columnMap.get("type"));
        column.setFilterable((Boolean) columnMap.get("filterable"));
        column.setSortable((Boolean) columnMap.get("sortable"));
        column.setSearchable((Boolean) columnMap.get("searchable"));
        return column;
    }

    public static Row convertToRow(Map<String, Object> rowMap) {
        Row row = new Row();
        row.setId(((Number) rowMap.get("id")).longValue());
        row.setData((String) rowMap.get("data"));
        return row;
    }

    public static Pagination convertToPagination(Map<String, Object> paginationMap) {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(((Number) paginationMap.get("currentPage")).longValue());
        pagination.setTotalPages(((Number) paginationMap.get("totalPages")).longValue());
        pagination.setPageSize(((Number) paginationMap.get("pageSize")).longValue());
        pagination.setTotalItems(((Number) paginationMap.get("totalItems")).longValue());
        return pagination;
    }

    public static FilterTable convertToFilter(Map<String, Object> filterMap) {
        FilterTable filter = new FilterTable();
        filter.setName(((String) filterMap.get("name")));
        filter.setAge(((String) filterMap.get("age")));
        filter.setRole(((String) filterMap.get("role")));
        return filter;
    }
}
