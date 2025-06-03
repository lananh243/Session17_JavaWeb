package com.data.demo1.repository;

import com.data.demo1.entity.ProductCart;

import java.util.List;

public interface CartRepository {
    List<ProductCart> findAll();
    void updateQuantityById(int id, int quantity);

    void delete(int  cartId);
    ProductCart findCartById(int id);
}
