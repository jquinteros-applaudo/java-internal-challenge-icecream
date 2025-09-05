package com.applaudo.project.model;

import lombok.*;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CombineIceCreamRequest {

    @NotEmpty(message = "At least one ice cream must be provided")
    @Valid
    private List<IceCreamDto> iceCreams;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Percentage must be greater than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Percentage must be less than or equal to 100")
    private Double percentage; // 60 as value, you have to convert to decimal. could can get 60.12%
}
