package com.applaudo.project.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CombineIceCreamRequest {

    private List<IceCreamDto> iceCreams;
    private Double percentage; // 60 as value, you have to convert to decimal. could can get 60.12%
}
