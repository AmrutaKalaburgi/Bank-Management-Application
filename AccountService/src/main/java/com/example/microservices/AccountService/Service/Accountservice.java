package com.example.microservices.AccountService.Service;

import com.example.microservices.AccountService.Model.Account;
import com.example.microservices.AccountService.Repository.AccountRepo;
import com.example.microservices.AccountService.client.Customerclient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RefreshScope
public class Accountservice {

    @Value("${custom.message}")
    private String message;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    Customerclient customerclient;

    public void addAccount(Account account) {
        accountRepo.save(account);
    }

    public List<Account> GetAllAccounts() {

        return accountRepo.findAll()
                .stream()
                .map(account -> {
                    System.out.println("Fetching customer info for account ID: " + account.getCustomerId());
                    if (account.getCustomerId() != null) {
                        try {
                            account.setCustomer(customerclient.getCustomerInfo(account.getCustomerId()));
                        } catch (Exception e) {
                            System.err.println("Error fetching customer info for account ID: " + account.getCustomerId() + " - " + e.getMessage());
                            account.setCustomer(null);
                        }
                    }
                    return account;
                })
                .collect(Collectors.toList());
    }

    public Account GetAccountById(int id) {
        return accountRepo.findById(id).map(account -> {
            System.out.println("Fetching customer info for account ID: " + id);
            if (account.getCustomerId() != null) {
                try {
                    account.setCustomer(customerclient.getCustomerInfo(account.getCustomerId()));
                } catch (Exception e) {
                    System.err.println("Error fetching customer info for account ID: " + account.getCustomerId() + " - " + e.getMessage());
                    account.setCustomer(null);
                }
            }
            return account;
        }).orElse(null);
    }

    public String getMessage() {
        return message;
    }
}
