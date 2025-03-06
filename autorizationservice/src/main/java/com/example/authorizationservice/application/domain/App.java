package com.example.authorizationservice.application.domain;

import jakarta.persistence.*;
import com.example.authorizationservice.cell.domain.Cell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class App {

    @Id
    @SequenceGenerator(
            name = "app_seq",
            sequenceName = "app_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_seq")
    @Column(nullable = false)
    private Long appId;

    @Column(nullable = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "appId")
    private List<Cell> cells = new ArrayList<>();

}
