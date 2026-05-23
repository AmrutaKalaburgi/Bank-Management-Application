package com.example.microservices.CustomerService.Service;

import com.example.microservices.CustomerService.Model.Customer;
import com.example.microservices.CustomerService.Repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Customerservice {
    CustomerRepo customerRepo;

     public Customerservice(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    public void addCustomer(Customer customer){

         customerRepo.save(customer);

    }
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }
}


