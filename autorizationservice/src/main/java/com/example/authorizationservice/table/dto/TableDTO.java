package com.example.authorizationservice.table.dto;


import com.example.authorizationservice.table.domain.Column;
import com.example.authorizationservice.table.domain.FilterGroup;
import com.example.authorizationservice.table.validator.ValidTable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Schema(description = "DTO таблицы")
public class TableDTO {

        @Schema(
                description = "Колонки таблицы",
                example = "[{\"accessor\":\"col1\",\"header\":\"column1\"},{\"accessor\":\"col2\",\"header\":\"column2\"}]"
        )
        //@ValidTable
        @NotNull
        @Valid
        private List< @Valid Column> columns;

        @Schema(
                description = "Данные таблицы",
                example = "[" +
                        "{\"col1\":{\"value\":\"value1\",\"type\":\"INPUT\"},\"col2\":{\"value\":\"value2\",\"type\":\"INPUT\"}}," +
                        "{\"col1\":{\"value\":\"opt221\",\"type\":\"DROPDOWN\",\"active\":false,\"optionsList\":[\"opt1\",\"opt221\",\"opt133\"]}," +
                        "\"col2\":{\"value\":\"opt3\",\"type\":\"DROPDOWN\",\"optionsList\":[\"opt3\",\"opt4\"]}}," +
                        "{\"col1\":{\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"value\":\"smth\",\"type\":\"INPUT\"}}," +
                        "{\"col1\":{\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"value\":\"true\",\"type\":\"CHECKBOX\"}}," +
                        "{\"col1\":{\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"value\":\"true\",\"type\":\"CHECKBOX\"}}," +
                        "{\"col1\":{\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"value\":\"true\",\"type\":\"CHECKBOX\"}}," +
                        "{\"col1\":{\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"value\":\"data\",\"type\":\"INPUT\"}}" +
                        "]"
        )
        @ValidTable
        private List<Map<String, Object>> data = new ArrayList<>();

        @Schema(
                description = "Группа фильтров",
                example = "{\"name\":\"filterGroupItem\",\"label\":\"item1\",\"filters\":[{\"name\":\"1\",\"type\":\"type1\",\"label\":\"label1\"},{\"name\":\"2\",\"type\":\"type2\",\"label\":\"label2\"}]}"
        )
        @ValidTable
        @NotNull
        private FilterGroup filterGroup;


}