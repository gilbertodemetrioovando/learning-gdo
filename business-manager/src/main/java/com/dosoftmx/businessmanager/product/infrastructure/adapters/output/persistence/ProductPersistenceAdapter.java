package com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence;

import java.util.List;
import java.util.Optional;

import com.dosoftmx.businessmanager.product.application.ports.output.ProductUseCase;
import com.dosoftmx.businessmanager.product.domain.model.Product;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.entity.ProductEntity;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.mapper.ProductMapper;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductUseCase {

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
