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
    public void updateQuantityById(int id, int quantity) {
        cartRepo.updateQuantityById(id, quantity);
    }

    @Override
    public void delete(int cartId) {
        cartRepo.delete(cartId);
    }

    @Override
    public ProductCart findCartById(int id) {
        return cartRepo.findCartById(id);
    }

}
