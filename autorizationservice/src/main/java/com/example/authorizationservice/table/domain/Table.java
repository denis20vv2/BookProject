package com.example.authorizationservice.table.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@jakarta.persistence.Table(name = "\"table\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Table {

    @Id
    @SequenceGenerator(
            name = "table_seq",
            sequenceName = "table_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_seq")
    private Long id;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Column> columns;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Map<String, Object>> data;

    @JdbcTypeCode(SqlTypes.JSON)
    private FilterGroup filterGroup ;

    public Table(List<Column> columns, List<Map<String, Object>> data, FilterGroup filterGroup ){
        this.columns = columns;
        this.data = data;
        this.filterGroup = filterGroup;
    }

}
