package com.example.microservices.CustomerService.Repository;

import com.example.microservices.CustomerService.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
