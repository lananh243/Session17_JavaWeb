package com.data.demo1.repository;

import com.data.demo1.entity.Customer;

public interface CustomerRepository {
    void save(Customer customer);
    Customer findById(int customerId);
}
