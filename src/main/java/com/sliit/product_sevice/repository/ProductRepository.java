package com.sliit.product_sevice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sliit.product_sevice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
