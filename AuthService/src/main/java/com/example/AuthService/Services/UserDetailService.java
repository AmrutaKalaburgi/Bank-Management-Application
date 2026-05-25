package com.example.AuthService.Services;

import com.example.AuthService.UserRepository.UserCredRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {
        @Autowired
        private UserCredRepo userRepo;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username: "));
        }
}
