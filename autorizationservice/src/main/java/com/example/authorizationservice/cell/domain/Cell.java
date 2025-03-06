package com.example.authorizationservice.cell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "cell")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cell {

    @Id
    @SequenceGenerator(
            name = "cell_seq",
            sequenceName = "cell_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cell_seq")
    @Column(nullable = false)
    private Long cellId;

    @Column(nullable = true)
    private Long cellPositionX;

    @Column(nullable = true)
    private Long cellPositionY;

    @Column(nullable = true)
    private Long appId;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Data data;

    public Cell(Long cellPositionX, Long cellPositionY, Long appId, Data data){
        this.cellPositionX = cellPositionX;
        this.cellPositionY = cellPositionY;
        this.appId = appId;
        this.data = data;
    }

}
