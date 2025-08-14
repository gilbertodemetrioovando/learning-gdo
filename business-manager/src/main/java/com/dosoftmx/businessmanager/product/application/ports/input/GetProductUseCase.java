package com.dosoftmx.businessmanager.product.application.ports.input;

import java.util.List;

import com.dosoftmx.businessmanager.product.domain.exception.ProductNotFoundException;
import com.dosoftmx.businessmanager.product.domain.model.Product;

public interface GetProductUseCase {
	Product getProductById(Long id) throws ProductNotFoundException;
	List<Product> getProductAll() throws ProductNotFoundException;
}
