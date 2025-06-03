package com.data.demo1.service;

import com.data.demo1.entity.ProductCart;

import java.util.List;

public interface CartService {
    List<ProductCart> findAll();
    void update(ProductCart productCart);
    void delete(int productId);
}
