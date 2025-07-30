package com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.entity.ProductEntity;


public interface ProductRepository extends JpaRepository <ProductEntity, Long> {

}
