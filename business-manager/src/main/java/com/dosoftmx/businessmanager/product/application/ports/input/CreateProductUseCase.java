package com.dosoftmx.businessmanager.product.application.ports.input;

import com.dosoftmx.businessmanager.product.domain.model.Product;

public interface CreateProductUseCase {

	Product createProduct(Product product);
}
