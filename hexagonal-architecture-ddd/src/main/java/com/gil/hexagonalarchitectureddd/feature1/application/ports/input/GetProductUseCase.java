package com.gil.hexagonalarchitectureddd.feature1.application.ports.input;

import java.util.List;

import com.gil.hexagonalarchitectureddd.feature1.domain.exception.ProductNotFoundException;
import com.gil.hexagonalarchitectureddd.feature1.domain.model.Product;

public interface GetProductUseCase {
	Product getProductById(Long id) throws ProductNotFoundException;
	List<Product> getProductAll() throws ProductNotFoundException;
}
