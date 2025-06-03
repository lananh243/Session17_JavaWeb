package com.data.demo1.controller;

import com.data.demo1.entity.Customer;
import com.data.demo1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/add-customer")
    public String addCustomer(@Valid Customer customer,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        customerService.save(customer);
        model.addAttribute("success", "Đăng kí thành công");
        return "register";
    }
}

