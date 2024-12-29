package com.example.musinsa.pricingSystem.apiSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BRAND")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    @Column(name = "ID", nullable = false, updatable = false)
    private Long brandId;

    @Size(max = 50)
    @NotNull
    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;

}