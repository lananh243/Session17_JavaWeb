package com.data.demo1.service;

import com.data.demo1.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll(int pageNumber, int pageSize);
    Product findById(int id);
}
