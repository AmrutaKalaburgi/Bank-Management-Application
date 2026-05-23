package com.example.microservices.CustomerService.Controller;

import com.example.microservices.CustomerService.Model.Customer;
import com.example.microservices.CustomerService.Service.Customerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    Customerservice customerservice;

    @PostMapping("/addcustomer")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        customerservice.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getcustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerservice.getAllCustomers());
    }

    @GetMapping("/getcustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer=customerservice.getCustomerById(id);
        if(customer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }
    }
}
