package com.applaudo.project.domain;

import com.applaudo.project.model.IceCreamDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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
@Entity
@Table(name = "icecreams")
public class IceCreamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    @Positive
    private BigDecimal cost;

    public IceCreamDto toDto() {
        return IceCreamDto.builder()
                .id(id)
                .name(name)
                .cost(cost)
                .build();
    }
}
