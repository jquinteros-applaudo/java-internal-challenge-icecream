package com.applaudo.project.model;

import lombok.*;

import java.math.BigDecimal;

import com.applaudo.project.domain.IceCreamEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

/**
 * Don't change it
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateIceCreamDto {

    @NonNull
    @NotEmpty(message = "Name must not be empty")
    private String name;
    
    @Positive(message = "Cost must be positive")
    @NonNull
    private BigDecimal cost;

    @Override
    public String toString() {
        return "CreateIceCreamDto{" +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    public IceCreamEntity toIceCream(){
        return IceCreamEntity.builder().name(this.name).cost(this.cost).build();
    }
}
