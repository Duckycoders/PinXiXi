package com.example.mini_amazon.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mini_amazon.service.ProductService;
import com.example.mini_amazon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/notReview")
    public List<Product> getAllProductsNotReview() {
        return productService.getAllProductsNotReview();
    }

    @GetMapping("/reviewed")
    public List<Product> getAllProductIdsReviewed() {
        return productService.getAllProductIdsReviewed();
    }

    @GetMapping("/search")
    public IPage<Product> searchProducts(@RequestParam String keyword, @RequestParam Integer sellerId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        return productService.searchProducts(keyword, sellerId, page, limit);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public void addProduct(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam(value = "imageUrl", required = false) String imageUrl,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {

        Product product = new Product();
        if(id != null && !id.isEmpty()) {
            product.setId(Integer.parseInt(id));
        }
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategoryId(categoryId);
        product.setSellerId(UserStorage.getCurrentUser().getSellerId());

        // 处理图片文件，存储为二进制数据
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImage(imageFile.getBytes());
            product.setImageUrl(imageUrl);
        }
        if (product.getId() == null) {
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