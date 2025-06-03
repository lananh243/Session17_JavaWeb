package com.data.demo1.service;

import com.data.demo1.entity.ProductCart;
import com.data.demo1.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartServiceImp implements ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepo;
    @Override
    public void addProduct(int customerId, int productId, int quantity) {
        productCartRepo.addProduct(customerId, productId, quantity);
    }

    @Override
    public List<ProductCart> findAllByCustomerId(int customerId) {
        return productCartRepo.findAllByCustomerId(customerId);
    }

    @Override
    public void clearCartByCustomerId(int customerId) {
        productCartRepo.clearCartByCustomerId(customerId);
    }
}
