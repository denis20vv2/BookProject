package com.example.authorizationservice.cell.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "page")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Page {

    @Id
    @SequenceGenerator(
            name = "page_seq",
            sequenceName = "page_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "page_seq")
    @Column(nullable = false)
    private Long pageId;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Data data;

    public Page(Data data) {
        this.data = data;
    }

}
