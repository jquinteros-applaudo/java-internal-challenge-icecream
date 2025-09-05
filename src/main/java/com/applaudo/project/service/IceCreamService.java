package com.applaudo.project.service;

import com.applaudo.project.domain.IceCreamEntity;
import com.applaudo.project.model.exceptions.IceCreamNotFoundException;
import com.applaudo.project.repository.IceCreamRepository;
import lombok.RequiredArgsConstructor;
import com.applaudo.project.model.IceCreamDto;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IceCreamService {

    private final IceCreamRepository iceCreamRepository;

    // TODO can use IceCreamCombinator class

    private IceCreamEntity getOneEntityByIdOrFail(Long iceCreamdId) {
        IceCreamEntity iceCream = this.iceCreamRepository.findById(iceCreamdId)
                .orElseThrow(() -> new IceCreamNotFoundException(iceCreamdId));
        return iceCream;
    }

    public IceCreamDto getOneByIdOrFail(Long iceCreamdId) {
        IceCreamEntity iceCream = this.getOneEntityByIdOrFail(iceCreamdId);
        return iceCream.toDto();
    }

}
