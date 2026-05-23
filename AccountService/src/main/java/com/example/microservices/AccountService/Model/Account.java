package com.example.microservices.AccountService.Model;

import com.example.microservices.AccountService.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account {
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 private Long id;
 private String accountNumber;
 private String accountType;
 private Double balance;
 private Long customerId;  // FK — references customer.id in CustomerService

 @Transient  // not stored in DB, populated at runtime via Feign
 private CustomerDTO customer;


}
