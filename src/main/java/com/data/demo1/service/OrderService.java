package com.data.demo1.service;

import com.data.demo1.entity.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);
    List<Order> findByCustomerId(Integer customerId);
    List<Order> findAll();
    Order findById(Integer id);
}
