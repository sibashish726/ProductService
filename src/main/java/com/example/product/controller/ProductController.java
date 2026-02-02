package com.example.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.model.ProductRequest;
import com.example.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
   @Autowired
   private ProductService productService;
   
   @PostMapping("/addProduct")
   public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
	   long productId= productService.addProduct(productRequest);
	   return new ResponseEntity<Long>(productId, HttpStatus.CREATED);
   }
}
