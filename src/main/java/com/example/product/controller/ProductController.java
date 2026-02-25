package com.example.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.model.ProductRequest;
import com.example.product.model.ProductResponse;
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
   
   @GetMapping("/getProductById/{id}")
   public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
	   ProductResponse productResponse= productService.getProductById(productId);
	   return new ResponseEntity<>(productResponse, HttpStatus.OK);
   }
   
   @PutMapping("/reduceQuantity/{id}")
   public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity){
	   productService.reduceQuantity(productId,quantity);
	   return new ResponseEntity<>(HttpStatus.OK);
	   
   }
   @DeleteMapping("/deleteProduct/{id}")
   public ResponseEntity<Void> deleteProductById(@PathVariable("id") long productId) {
       productService.deleteProductById(productId);
       return new ResponseEntity<>(HttpStatus.OK);
   }
   @GetMapping("/getAllProducts")
   public ResponseEntity<Page<ProductResponse>> getAllProducts(
           @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize
   ) {
       Page<ProductResponse> productResponses = productService.getAllProducts(pageNumber, pageSize);
       return new ResponseEntity<>(productResponses, HttpStatus.OK);
   }
   
   
}
