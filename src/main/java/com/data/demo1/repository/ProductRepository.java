package com.data.demo1.repository;

import com.data.demo1.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll(int pageNumber, int pageSize);
    Product findById(int id);
}
