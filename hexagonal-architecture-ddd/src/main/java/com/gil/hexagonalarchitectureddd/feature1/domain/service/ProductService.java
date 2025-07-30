package com.gil.hexagonalarchitectureddd.feature1.domain.service;

import java.util.List;

import com.gil.hexagonalarchitectureddd.feature1.application.ports.input.CreateProductUseCase;
import com.gil.hexagonalarchitectureddd.feature1.application.ports.input.GetProductUseCase;
import com.gil.hexagonalarchitectureddd.feature1.application.ports.output.ProductOutputPort;
import com.gil.hexagonalarchitectureddd.feature1.domain.exception.ProductNotFoundException;
import com.gil.hexagonalarchitectureddd.feature1.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

	private final ProductOutputPort productOutputPort;
	
	@Override
	public Product getProductById(Long id) throws ProductNotFoundException {
		System.out.println("Retornando Producto por ID");
		return productOutputPort.getProductById(id)
				.orElseThrow(() -> new ProductNotFoundException("No se encontro el producto con ID: " + id));
	}

	@Override
	public Product createProduct(Product product) {
		System.out.println("Creando Producto");
		return productOutputPort.saveProduct(product);
	}

	@Override
	public List<Product> getProductAll() throws ProductNotFoundException {
		log.info("get All products");
		return this.productOutputPort.getProductAll()
				.orElseThrow(() -> new ProductNotFoundException("No hay productos "));
	}

}
