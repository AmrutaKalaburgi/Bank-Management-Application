package com.example.microservices.AccountService.Repository;

import com.example.microservices.AccountService.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {

}
