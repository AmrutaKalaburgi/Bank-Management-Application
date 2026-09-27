package com.example.AuthService.Services;

import com.example.AuthService.UserRepository.UserCredRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
        @Autowired
        private UserCredRepo userRepo;
        @Override
        public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
            return userRepo.findByName(name).orElseThrow(()-> new UsernameNotFoundException("User not found with username: "));
        }
}
