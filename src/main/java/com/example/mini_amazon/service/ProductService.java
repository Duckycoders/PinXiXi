package com.example.mini_amazon.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini_amazon.mapper.CartMapper;
import com.example.mini_amazon.mapper.ProductMapper;
import com.example.mini_amazon.model.Product;
import com.example.mini_amazon.model.ProductDTO;
import com.example.mini_amazon.model.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartMapper cartMapper;

    public List<Product> getAllProducts() {
        // // 创建分页对象，设置当前页码和每页显示的记录数
        // Page<Product> pageObj = new Page<>(page, limit);
        // return productMapper.selectPage(pageObj, new QueryWrapper<>());
        // int offset = (page - 1) * limit;
        return productMapper.findAll();
    }

    public IPage<Product> searchProducts(String keyword, Integer sellerId, int page, int limit) {
        Page<Product> pageObj = new Page<>(page, limit);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("seller_id", sellerId).like("name", keyword).like("description", keyword);
        return productMapper.selectPage(pageObj, wrapper);
        // int offset = (page - 1) * limit;
        // return productMapper.searchByKeyword(keyword, offset, limit);
    }

    public void addProduct(Product product) {
        product.setId(productMapper.getCurrentId() + 1);
        productMapper.addProduct(product);
        productMapper.addProductInventory(product);
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return productMapper.findByCategory(categoryId);
    }

    public Product getProductById(Integer id) {
        return productMapper.findById(id);
    }

    public void deleteProduct(Integer id) {
        productMapper.deleteProductInventory(id);
        productMapper.deleteProduct(id);
        productMapper.deleteProductReviews(id);
        cartMapper.deleteCartItemByProductId(id);
    }

    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
        productMapper.updateProductInventory(product);
    }

    public List<ProductDTO> getProductsBySellerId(Long sellerId) {
        return productMapper.getProductsBySellerId(sellerId);
    }

    public void updateProductQuantity(Long productId, Integer quantity) {
        productMapper.updateProductQuantity(productId, quantity);
    }

    public List<Product> getAllProductsNotReview() {
        return productMapper.getAllProductIdsNotReview(UserStorage.getCurrentUser().getId());
    }

    public List<Product> getAllProductIdsReviewed() {
        return productMapper.getAllProductIdsReviewed(UserStorage.getCurrentUser().getId());
    }
}