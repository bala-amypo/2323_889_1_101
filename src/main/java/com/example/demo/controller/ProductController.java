// package com.example.demo.controller;

// import com.example.demo.model.Product;
// import com.example.demo.service.ProductService;
// import jakarta.validation.Valid;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/products")   // 🔥 MUST BE /api/products
// public class ProductController {

//     private final ProductService productService;

//     public ProductController(ProductService productService) {
//         this.productService = productService;
//     }

//     @PostMapping
//     public ResponseEntity<Product> createProduct(
//             @Valid @RequestBody Product product) {

//         return ResponseEntity.ok(productService.createProduct(product));
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Product> getProduct(@PathVariable Long id) {
//         return ResponseEntity.ok(productService.getProduct(id));
//     }

//     @GetMapping
//     public ResponseEntity<List<Product>> listProducts() {
//         return ResponseEntity.ok(productService.getAllProducts());
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid; // Added import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    // Added @Valid annotation to trigger validation before the mocked service is called
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}