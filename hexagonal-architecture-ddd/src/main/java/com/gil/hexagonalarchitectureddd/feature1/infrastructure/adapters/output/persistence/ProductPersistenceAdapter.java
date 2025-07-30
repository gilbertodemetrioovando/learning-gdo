package com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import com.gil.hexagonalarchitectureddd.feature1.application.ports.output.ProductOutputPort;
import com.gil.hexagonalarchitectureddd.feature1.domain.model.Product;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.entity.ProductEntity;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.mapper.ProductMapper;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	@Override
	public Product saveProduct(Product product) {
		ProductEntity productEntity = productMapper.toEntity(product);
		productRepository.save(productEntity);
		return productMapper.toProduct(productEntity);
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		Optional<ProductEntity> productEntity = productRepository.findById(id);

		if (productEntity.isEmpty()) {
			return Optional.empty();
		}

		Product product = productMapper.toProduct(productEntity.get());
		return Optional.of(product);
	}

	@Override
	public Optional<List<Product>> getProductAll() {
		Optional<List<ProductEntity>> productsEntity = Optional.of(productRepository.findAll());
		return productsEntity.map(productMapper::toListProduct);
	}

}
