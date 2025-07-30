package com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.input;

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

import com.gil.hexagonalarchitectureddd.feature1.application.ports.input.CreateProductUseCase;
import com.gil.hexagonalarchitectureddd.feature1.application.ports.input.GetProductUseCase;
import com.gil.hexagonalarchitectureddd.feature1.domain.exception.ProductNotFoundException;
import com.gil.hexagonalarchitectureddd.feature1.domain.model.Product;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.rest.data.request.ProductRequest;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.rest.data.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/feature1/")
@RequiredArgsConstructor
public class ProductRestAdapter {

	private final CreateProductUseCase createProductUseCase;

	private final GetProductUseCase getProductUseCase;

	private final ModelMapper mapper;

	@PostMapping(value = "/products")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productToCreate) {
		// Request to domain
		Product product = mapper.map(productToCreate, Product.class);
		product = createProductUseCase.createProduct(product);
		// Domain to response
		return new ResponseEntity<>(mapper.map(product, ProductResponse.class), HttpStatus.CREATED);
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
		Product product = null;
		try {
			product = getProductUseCase.getProductById(id);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}
		// Domain to response
		return new ResponseEntity<>(mapper.map(product, ProductResponse.class), HttpStatus.OK);
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
