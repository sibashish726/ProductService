package com.example.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.model.ProductRequest;
import com.example.product.repository.ProductRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo produRepo;
	
	@Override
	public long addProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		log.info("Adding product");
		Product product= Product.builder()
				                 .productName(productRequest.getName())
				                 .quantity(productRequest.getQuantity())
				                 .price(productRequest.getPrice())
				                 .build();
		produRepo.save(product);
		log.info("product added");
		return product.getProductId();
	}

}
