package com.data.demo1.repository;

import com.data.demo1.entity.Customer;

public interface LoginRepository {
    Customer findByCustomer(String username, String password);
}
