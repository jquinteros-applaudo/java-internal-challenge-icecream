package com.applaudo.project.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class APIError {

    private int code; // Its the same HTTP status
    private String description;
}
