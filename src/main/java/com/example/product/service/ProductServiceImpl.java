package com.example.product.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.exception.ProductServiceCustomException;
import com.example.product.model.ProductRequest;
import com.example.product.model.ProductResponse;
import com.example.product.repository.ProductRepo;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public long addProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		log.info("Adding product");
		Product product= Product.builder()
				                 .productName(productRequest.getName())
				                 .quantity(productRequest.getQuantity())
				                 .price(productRequest.getPrice())
				                 .build();
		productRepo.save(product);
		log.info("product added");
		return product.getProductId();
	}

	@Override
	@Cacheable(value = "product", key = "#productId")
	public ProductResponse getProductById(long productId) {
		log.info("Get product for Product id: "+productId);
		Product product= productRepo.findById(productId)
				        .orElseThrow(() ->new ProductServiceCustomException("Product not found with id "+productId,"PRODUCT_NOT_FOUND"));
		ProductResponse productResponse= new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	@CacheEvict(value = "product", key = "#productId")
	public void reduceQuantity(long productId, long quantity) {
		// TODO Auto-generated method stub
		log.info("Reduce quantity of "+quantity +" for product: "+productId);
		Product product= productRepo.findById(productId)
		        .orElseThrow(() ->new ProductServiceCustomException("Product not found with id "+productId,"PRODUCT_NOT_FOUND"));
        
		if(product.getQuantity()< quantity) {
        	throw new ProductServiceCustomException("Product doesn't have sufficient quantity ","INSUFFICIENT_QUANTITY");
        }
		product.setQuantity(product.getQuantity() - quantity);
		productRepo.save(product);
		log.info("Product quantity updated successfully");
	}

	@Override
	@CacheEvict(value = "product", key = "#productId")
	public void deleteProductById(long productId) {
		log.info("Delete Product for Product id: " + productId);
	    try {
	        productRepo.deleteById(productId);
	        log.info("Product deleted successfully with id: " + productId);
	    } catch (Exception e) {
	        log.error("Error occurred while deleting product: " + e.getMessage());
	        throw new ProductServiceCustomException(
	                "Product with id " + productId + " not found",
	                "PRODUCT_NOT_FOUND"
	        );
	    }
		
	}

	@Override
	@Cacheable(value = "products_page", key = "#pageNumber + '-' + #pageSize")
	public Page<ProductResponse> getAllProducts(int pageNumber, int pageSize) {
		log.info("Fetching all products with pageNumber: {} and pageSize: {}", pageNumber, pageSize);
	    
	    Pageable pageable = PageRequest.of(pageNumber, pageSize);
	    Page<Product> productPage = productRepo.findAll(pageable);
	    

	    Page<ProductResponse> productResponsePage = productPage.map(product -> {
	        ProductResponse productResponse = new ProductResponse();
	        BeanUtils.copyProperties(product, productResponse);
	        return productResponse;
	    });
	    
	    log.info("Successfully fetched products");
	    return productResponsePage;
	}
	
	

}
