package com.example.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="product")
public class Product {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long productId;
   
   @Column(name="PRODUCT_NAME")
   private String productName;
   
   @Column(name="PRICE")
   private long price;
   
   @Column(name="QUANTITY")
   private long quantity;
}
