package com.data.demo1.service;

import com.data.demo1.entity.ProductCart;
import com.data.demo1.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepository cartRepo;
    @Override
    public List<ProductCart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public void update(ProductCart productCart) {
        cartRepo.update(productCart);
    }

    @Override
    public void delete(int productId) {
        cartRepo.delete(productId);
    }
}
