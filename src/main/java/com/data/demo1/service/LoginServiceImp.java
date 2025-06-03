package com.data.demo1.service;

import com.data.demo1.entity.Customer;
import com.data.demo1.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService{
    @Autowired
    private LoginRepository loginRepo;

    @Override
    public Customer findByCustomer(String username, String password) {
        return loginRepo.findByCustomer(username, password);
    }
}
