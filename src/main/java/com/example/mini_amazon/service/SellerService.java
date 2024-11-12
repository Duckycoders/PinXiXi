package com.example.mini_amazon.service;

import com.example.mini_amazon.mapper.SellerMapper;
import com.example.mini_amazon.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    public int addSeller(Seller seller) {
        return sellerMapper.insertSeller(seller);
    }

    public Seller findSellerById(int id) {
        return sellerMapper.getSellerById(id);
    }

    public int updateSeller(Seller seller) {
        return sellerMapper.updateSeller(seller);
    }

    public int deleteSeller(int id) {
        return sellerMapper.deleteSeller(id);
    }

    public List<Seller> findAllSellers() {
        return sellerMapper.getAllSellers();
    }
}