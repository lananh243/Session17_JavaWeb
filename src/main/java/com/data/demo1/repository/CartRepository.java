package com.data.demo1.repository;

import com.data.demo1.entity.ProductCart;

import java.util.List;

public interface CartRepository {
    List<ProductCart> findAll();
    void update(ProductCart productCart);
    void delete(int productId);
}
