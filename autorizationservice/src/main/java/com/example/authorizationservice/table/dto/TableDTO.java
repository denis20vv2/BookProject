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
                        "{\"col1\":{\"id\":\"id1\",\"value\":\"value1\",\"type\":\"INPUT\"},\"col2\":{\"id\":\"id2\",\"value\":\"value2\",\"type\":\"INPUT\"}}," +
                        "{\"col1\":{\"id\":\"id3\",\"value\":\"opt221\",\"type\":\"DROPDOWN\",\"active\":false,\"optionsList\":[\"opt1\",\"opt221\",\"opt133\"]}," +
                        "\"col2\":{\"id\":\"id4\",\"value\":\"opt3\",\"type\":\"DROPDOWN\",\"optionsList\":[\"opt3\",\"opt4\"]}}," +
                        "{\"col1\":{\"id\":\"id5\",\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"id\":\"id6\",\"value\":\"smth\",\"type\":\"INPUT\"}}," +
                        "{\"col1\":{\"id\":\"id7\",\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"id\":\"id8\",\"value\":\"true\",\"type\":\"CHECKBOX\"}}," +
                        "{\"col1\":{\"id\":\"id9\",\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"id\":\"id10\",\"value\":\"true\",\"type\":\"CHECKBOX\"}}," +
                        "{\"col1\":{\"id\":\"id11\",\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"id\":\"id12\",\"value\":\"true\",\"type\":\"CHECKBOX\"}}," +
                        "{\"col1\":{\"id\":\"id13\",\"value\":\"true\",\"type\":\"CHECKBOX\"},\"col2\":{\"id\":\"id14\",\"value\":\"data\",\"type\":\"INPUT\"}}" +
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