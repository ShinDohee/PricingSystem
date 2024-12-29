package com.example.musinsa.pricingSystem.apiSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    @Column(name = "ID", nullable = false)
    private Long categoryId;

    @Size(max = 50)
    @NotNull
    @Column(name = "CATEGORY_NAME", nullable = false, length = 50)
    private String categoryName;

}