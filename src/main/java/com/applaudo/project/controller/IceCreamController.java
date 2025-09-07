package com.applaudo.project.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applaudo.project.service.IceCreamService;
import com.applaudo.project.model.CombineIceCreamRequest;
import com.applaudo.project.model.CreateIceCreamDto;
import com.applaudo.project.model.IceCreamDto;

import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@Validated()
@RequestMapping("/api/ice-cream")
public class IceCreamController {
    private final IceCreamService iceCreamService;

    @GetMapping()
    public List<IceCreamDto> getAll(@Param("searchName") Optional<String> searchName) {
        return this.iceCreamService.getAllIceCreams(searchName);
    }


    @GetMapping("/{id}")
    public IceCreamDto getOneByIdOrFail(@PathVariable(name = "id")  Long id) {
        return this.iceCreamService.getOneByIdOrFail(id);
    }


    @PostMapping()
    public IceCreamDto createIceCreams(@Valid @RequestBody CreateIceCreamDto createIceCreamDto) {
        return this.iceCreamService.createIceCream(createIceCreamDto);
    }

    @PostMapping("/combine")
    public IceCreamDto combineIceCreams(@Valid @RequestBody CombineIceCreamRequest combineIceCreamRequest) {
        return this.iceCreamService.combineIceCreams(combineIceCreamRequest);
    }
}
