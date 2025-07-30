package com.gil.hexagonalarchitectureddd.feature1.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.gil.hexagonalarchitectureddd.feature1.domain.model.Product;

public interface ProductOutputPort {

	Product saveProduct(Product product);

	Optional<Product> getProductById(Long id);
	
	Optional<List<Product>> getProductAll();

}
