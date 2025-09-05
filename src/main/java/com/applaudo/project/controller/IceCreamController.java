package com.applaudo.project.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applaudo.project.service.IceCreamService;
import com.applaudo.project.model.CombineIceCreamRequest;
import com.applaudo.project.model.IceCreamDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequiredArgsConstructor
@RestController
@Validated()
@RequestMapping("/api/ice-cream")
public class IceCreamController {
    private final IceCreamService iceCreamService;

    @GetMapping("/{id}")
    public IceCreamDto getOneByIdOrFail(@PathVariable(name = "id") @Min(1) Long id) {
        return this.iceCreamService.getOneByIdOrFail(id);
    }

    @PostMapping("/combine")
    public IceCreamDto combineIceCreams(@Valid @RequestBody CombineIceCreamRequest combineIceCreamRequest) {
        return this.iceCreamService.combineIceCreams(combineIceCreamRequest);
    }
}
