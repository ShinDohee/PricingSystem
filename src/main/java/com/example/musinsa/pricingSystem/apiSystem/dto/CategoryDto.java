package com.example.musinsa.pricingSystem.apiSystem.dto;

import com.example.musinsa.pricingSystem.apiSystem.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@Value
public class CategoryDto implements Serializable {
    @NotNull
    @Size(max = 50)
    String categoryname;
}