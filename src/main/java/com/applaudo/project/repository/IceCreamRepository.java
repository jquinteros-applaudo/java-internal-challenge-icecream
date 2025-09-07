package com.applaudo.project.repository;

import com.applaudo.project.domain.IceCreamEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCreamEntity, Long> {

    public List<IceCreamEntity> findByNameContainingIgnoreCase(String name);

    public Boolean existsByName(String name);

    public List<IceCreamEntity> findByIdInOrderByName(Iterable<Long> ids);
}
