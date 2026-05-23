package com.example.microservices.AccountService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phoneNumber;
    private String city;
}
