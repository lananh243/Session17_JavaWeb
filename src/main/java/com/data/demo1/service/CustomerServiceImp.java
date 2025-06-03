package com.data.demo1.service;

import com.data.demo1.entity.Customer;
import com.data.demo1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }
}
