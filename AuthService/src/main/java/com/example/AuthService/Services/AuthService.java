package com.example.AuthService.Services;

import com.example.AuthService.UserRepository.UserCredRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.AuthService.Entity.User;
@Service
public class AuthService {

    @Autowired
    private UserCredRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;
    public String saveuser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added successfully";
    }
    public String GenerateToken(String name){
            return jwtService.generateToken(name);

    }
//    public void ValidateToken(String token){
//        jwtService.validateToken(token);
//    }
}
