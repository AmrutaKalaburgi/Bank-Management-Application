package com.example.microservices.AccountService.Controller;

import com.example.microservices.AccountService.Model.Account;
import com.example.microservices.AccountService.Service.Accountservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/account")
public class AccountController {



    @GetMapping("/message")
    public String getMessage() {
        return accountservice.getMessage();
    }


    @Autowired
    Accountservice accountservice;

    @PostMapping("/addAccount")
    public ResponseEntity<Void> AddAccount(@RequestBody Account account){
        accountservice.addAccount(account);
        // Logic to add account
       return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List<Account>> GetAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountservice.GetAllAccounts());
    }

    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<Account> GetAccountById(@PathVariable int id){
        Account account=accountservice.GetAccountById(id);
        if(account==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(accountservice.GetAccountById(id));
        }
    }

}
