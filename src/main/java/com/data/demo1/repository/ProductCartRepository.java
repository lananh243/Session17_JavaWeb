package com.data.demo1.repository;

import com.data.demo1.entity.Product;
import com.data.demo1.entity.ProductCart;

import java.util.List;

public interface ProductCartRepository {
    void addProduct(int customerId, int productId, int quantity);
    List<ProductCart> findAllByCustomerId(int customerId);
    void clearCartByCustomerId(int customerId);
}
