package com.applaudo.project.repository;

import com.applaudo.project.domain.IceCreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCreamEntity, Long> {

    // TODO implement your queries
}
