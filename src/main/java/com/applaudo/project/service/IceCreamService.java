package com.applaudo.project.service;

import com.applaudo.project.domain.IceCreamEntity;
import com.applaudo.project.repository.IceCreamRepository;
import com.applaudo.project.util.IceCreamCombinator;

import lombok.RequiredArgsConstructor;

import com.applaudo.project.model.CombineIceCreamRequest;
import com.applaudo.project.model.IceCreamDto;
import com.applaudo.project.model.exceptions.IceCreamNotFoundException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IceCreamService {

    private final IceCreamRepository iceCreamRepository;


    private IceCreamEntity getOneEntityByIdOrFail(Long iceCreamdId) {
        IceCreamEntity iceCream = this.iceCreamRepository.findById(iceCreamdId)
                .orElseThrow(() -> new IceCreamNotFoundException(MessageFormat.format("IceCream {0} not found", iceCreamdId)));
        return iceCream;
    }

    public List<IceCreamDto> getAllIceCreams(Optional<String> searchName) {
        var iceCreams = searchName.isPresent() ? this.iceCreamRepository.findByNameContainingIgnoreCase(searchName.get()) : this.iceCreamRepository.findAll();

        return iceCreams.stream().map(IceCreamEntity::toDto).collect(Collectors.toList());
    }

    public IceCreamDto getOneByIdOrFail(Long iceCreamdId) {
        IceCreamEntity iceCream = this.getOneEntityByIdOrFail(iceCreamdId);
        return iceCream.toDto();
    }

    
    public IceCreamDto combineIceCreams(CombineIceCreamRequest combineIceCreamRequest) {
        List<Long> iceCreamIds = combineIceCreamRequest.getIceCreams().stream()
            .map(idDto -> idDto.getId())
            .collect(Collectors.toList());

        List<IceCreamDto> iceCreamDtos = this.iceCreamRepository.findByIdInOrderByName(iceCreamIds)
            .stream()
            .map(IceCreamEntity::toDto)
            .collect(Collectors.toList());
        
            if(iceCreamDtos.size() != iceCreamIds.size()) {
                String message = "Some of the IceCream are not found";
                throw new IceCreamNotFoundException(message);
            }
            
        iceCreamDtos.forEach(System.out::println);
        return IceCreamCombinator.createIceCreamCombination(iceCreamDtos, combineIceCreamRequest.getPercentage());
    }

}
