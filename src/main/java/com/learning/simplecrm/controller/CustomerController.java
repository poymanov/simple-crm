package com.learning.simplecrm.controller;

import com.learning.simplecrm.dao.CustomerDAO;
import com.learning.simplecrm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @RequestMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerDAO.getCustomers());

        return "list-customers";
    }
}
