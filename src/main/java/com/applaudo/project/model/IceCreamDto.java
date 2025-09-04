package com.applaudo.project.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Don't change it
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IceCreamDto {

    private Long id;
    private String name;
    private BigDecimal cost;
}
