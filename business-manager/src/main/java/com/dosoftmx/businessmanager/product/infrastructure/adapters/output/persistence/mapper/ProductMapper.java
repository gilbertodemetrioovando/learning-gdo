package com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.dosoftmx.businessmanager.product.domain.model.Product;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.entity.ProductEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper mapper;

    public Product toProduct(ProductEntity entity){
        return mapper.map(entity, Product.class);
    }
    
    public List<Product> toListProduct(List<ProductEntity> entities){
    	List<Product> listProducts = new ArrayList<>();
    	entities.forEach(entity -> 
    		listProducts.add(this.toProduct(entity))
    	);
        return listProducts;
    }
    
    public ProductEntity toEntity(Product product){
        return mapper.map(product, ProductEntity.class);
    }
}
