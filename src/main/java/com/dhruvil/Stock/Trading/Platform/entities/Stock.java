package com.dhruvil.Stock.Trading.Platform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    @Column(name = "company_name")
    private String companyName;

    private BigDecimal price;

    private int quantity;

    @OneToMany(mappedBy = "stock")
    @JsonIgnore
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();
}
