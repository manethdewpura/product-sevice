package com.sliit.product_sevice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sliit.product_sevice.entity.Product;
import com.sliit.product_sevice.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product", description = "Product CRUD API")
public class ProductController {

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@PostMapping
	@Operation(summary = "Create product", description = "Create a new product with name and price")
	@ApiResponse(responseCode = "201", description = "Product created")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product saved = productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping
	@Operation(summary = "Get all products", description = "Returns list of all products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get product by ID", description = "Returns a single product by id")
	@ApiResponse(responseCode = "404", description = "Product not found")
	public ResponseEntity<Product> getProductById(
			@Parameter(description = "Product id") @PathVariable Long id) {
		return productRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete product", description = "Deletes a product by id")
	@ApiResponse(responseCode = "204", description = "Product deleted")
	@ApiResponse(responseCode = "404", description = "Product not found")
	public ResponseEntity<Void> deleteProduct(
			@Parameter(description = "Product id") @PathVariable Long id) {
		if (!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		productRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
