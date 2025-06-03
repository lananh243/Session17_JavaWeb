package com.data.demo1.service;

import com.data.demo1.entity.Customer;

public interface CustomerService {
    void save(Customer customer);
    Customer findById(int customerId);
}
