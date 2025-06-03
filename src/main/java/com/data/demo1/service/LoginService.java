package com.data.demo1.service;

import com.data.demo1.entity.Customer;

public interface LoginService {
    Customer findByCustomer(String username, String password);
}
