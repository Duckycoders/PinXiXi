package com.example.mini_amazon.Service;

import com.example.mini_amazon.mapper.CartMapper;
import com.example.mini_amazon.mapper.ProductMapper;
import com.example.mini_amazon.model.Product;
import com.example.mini_amazon.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartMapper cartMapper;

    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    public void addProduct(Product product) {
        Integer id = productMapper.getCurrentId();
        product.setId(id + 1);
        productMapper.addProduct(product);
        productMapper.addProductInventory(product);
    }

    public List<Product> searchProducts(String keyword) {
        return productMapper.searchByKeyword(keyword);
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return productMapper.findByCategory(categoryId);
    }

    public Product getProductById(Integer id) {
        return productMapper.findById(id);
    }

    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
        cartMapper.deleteCartItemByProductId(id);
    }

    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    public List<ProductDTO> getProductsBySellerId(Long sellerId) {
        return productMapper.getProductsBySellerId(sellerId);
    }

    public void updateProductQuantity(Long productId, Integer quantity) {
        productMapper.updateProductQuantity(productId, quantity);
    }
}