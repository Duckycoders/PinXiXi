package com.example.mini_amazon.Controller;


import com.example.mini_amazon.Service.ProductService;
import com.example.mini_amazon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            // User currentUser = UserStorage.getCurrentUser();
            // product.setSellerId(currentUser.getId());
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
    }

    @PutMapping("/{id}")
    public void updateReview(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/seller/{sellerId}")
    public List<ProductDTO> getProductsBySellerId(@PathVariable Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<Void> updateProductQuantity(@PathVariable Long productId, @RequestBody Map<String, Integer> body) {
        productService.updateProductQuantity(productId, body.get("quantity"));
        return ResponseEntity.ok().build();
    }
}