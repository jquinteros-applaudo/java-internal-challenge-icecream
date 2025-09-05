package com.applaudo.project.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applaudo.project.domain.IceCreamEntity;
import com.applaudo.project.service.IceCreamService;
import com.applaudo.project.model.IceCreamDto;

import jakarta.validation.constraints.Min;

@RequiredArgsConstructor
@RestController
@Validated()
@RequestMapping("/api")
public class IceCreamController {
    private final IceCreamService iceCreamService;

    @GetMapping("ice-cream/{id}")
    public IceCreamDto getOneByIdOrFail(@PathVariable(name = "id") @Min(1) Long iceCreamId) {
        return iceCreamService.getOneByIdOrFail(iceCreamId);
    }

}
