package com.example.microservices.AccountService.Service;

import com.example.microservices.AccountService.Model.Account;
import com.example.microservices.AccountService.Repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Accountservice {
    @Autowired
    AccountRepo accountRepo;
    public void addAccount(Account account) {
        accountRepo.save(account);
    }

    public List<Account> GetAllAccounts() {
        return accountRepo.findAll();
    }

    public Account GetAccountById(int id) {
        return accountRepo.findById(id).orElse(null);
    }
}
