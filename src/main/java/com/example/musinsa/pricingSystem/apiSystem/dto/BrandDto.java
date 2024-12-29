package com.example.musinsa.pricingSystem.apiSystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.musinsa.pricingSystem.apiSystem.entity.Brand}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto implements Serializable {
    @NotNull
    @Size(max = 50)
    String brandName;


}