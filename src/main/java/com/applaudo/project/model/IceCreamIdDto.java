package com.applaudo.project.model;

import lombok.*;


import jakarta.validation.constraints.Positive;

/**
 * TODO: Receive only the ids from the request body, to keep data integrity on Price and Name for each record
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IceCreamIdDto {

    @Positive(message = "Id must be positive")
    private Long id;
}
