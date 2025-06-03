package com.data.demo1.service;

import com.data.demo1.entity.Product;
import com.data.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> findAll(int pageNumber, int pageSize) {
        return productRepo.findAll(pageNumber, pageSize);
    }

    @Override
    public Product findById(int id) {
        return productRepo.findById(id);
    }
}
