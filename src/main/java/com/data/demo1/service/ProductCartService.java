package com.data.demo1.service;

import com.data.demo1.entity.ProductCart;

import java.util.List;

public interface ProductCartService {
    void addProduct(int customerId, int productId, int quantity);
    List<ProductCart> findAllByCustomerId(int customerId);
}
