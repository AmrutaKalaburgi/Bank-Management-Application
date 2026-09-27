package com.example.AuthService.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.example.AuthService.Entity.User;

import java.util.Optional;

@Repository
public interface UserCredRepo extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
}
