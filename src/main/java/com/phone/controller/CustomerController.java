package com.phone.controller;

import java.util.List;
import java.util.logging.Logger;

import com.phone.model.Customer;

import com.phone.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    private static final Logger logger = Logger.getLogger(CustomerRepository.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public String create(@RequestBody Customer customer) {
        logger.info("POST API: Create a new customer");
        return customerRepository.create(customer);
    }

    @GetMapping("search/phone/{prefix}")
    public List<Customer> searchCustomerByPhone(@PathVariable final String prefix) {
        logger.info("GET API: Retrieve customers by phone prefix");
        return customerRepository.searchCustomerByPhone(prefix);
    }

    @GetMapping("/delete/{phone}")
    public String delete(@PathVariable final String phone) {
        logger.info("DELETE API: Delete a customer by phone");
        return customerRepository.delete(phone);
    }
}