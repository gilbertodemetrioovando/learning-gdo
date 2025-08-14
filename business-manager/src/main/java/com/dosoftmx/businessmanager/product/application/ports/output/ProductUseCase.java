package com.dosoftmx.businessmanager.product.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.dosoftmx.businessmanager.product.domain.model.Product;

public interface ProductUseCase {

	Product saveProduct(Product product);

	Optional<Product> getProductById(Long id);
	
	Optional<List<Product>> getProductAll();

}
