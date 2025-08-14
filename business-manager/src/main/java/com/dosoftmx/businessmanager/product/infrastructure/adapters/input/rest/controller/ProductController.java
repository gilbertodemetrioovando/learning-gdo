package com.dosoftmx.businessmanager.product.infrastructure.adapters.input.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dosoftmx.businessmanager.product.application.ports.input.CreateProductUseCase;
import com.dosoftmx.businessmanager.product.application.ports.input.GetProductUseCase;
import com.dosoftmx.businessmanager.product.domain.exception.ProductNotFoundException;
import com.dosoftmx.businessmanager.product.domain.model.Product;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.input.rest.dto.ProductRequestDTO;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.input.rest.dto.ProductResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/product/")
@RequiredArgsConstructor
public class ProductController {

	private final CreateProductUseCase createProductUseCase;

	private final GetProductUseCase getProductUseCase;

	private final ModelMapper mapper;

	@PostMapping(value = "/products")
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productToCreate) {
		// Request to domain
		Product product = mapper.map(productToCreate, Product.class);
		product = createProductUseCase.createProduct(product);
		// Domain to response
		return new ResponseEntity<>(mapper.map(product, ProductResponseDTO.class), HttpStatus.CREATED);
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
		Product product = null;
		try {
			product = getProductUseCase.getProductById(id);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}
		// Domain to response
		return new ResponseEntity<>(mapper.map(product, ProductResponseDTO.class), HttpStatus.OK);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> getProductAll() {
		List<Product> products = new ArrayList<>();
		try {
			products = getProductUseCase.getProductAll();
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}
		// Domain to response
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
