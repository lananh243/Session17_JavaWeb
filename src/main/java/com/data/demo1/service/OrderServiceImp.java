package com.data.demo1.service;

import com.data.demo1.entity.Order;
import com.data.demo1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderRepository orderRepo;
    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }

    @Override
    public List<Order> findByCustomerId(Integer customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepo.findById(id);
    }
}
