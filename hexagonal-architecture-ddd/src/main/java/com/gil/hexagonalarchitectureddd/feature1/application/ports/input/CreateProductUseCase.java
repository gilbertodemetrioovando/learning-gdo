package com.gil.hexagonalarchitectureddd.feature1.application.ports.input;

import com.gil.hexagonalarchitectureddd.feature1.domain.model.Product;

public interface CreateProductUseCase {

	Product createProduct(Product product);
}
