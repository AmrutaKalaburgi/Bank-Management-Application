package com.example.AuthService.Controller;

import com.example.AuthService.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.AuthService.Entity.User;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public String addNewUser(@RequestBody User user){
        return  authService.saveuser(user);
    }

    @GetMapping("/token")
    public String getToken(@RequestBody User user ){
       Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
          if(authenticate.isAuthenticated()){
              return authService.GenerateToken(user.getName());
          }
          else {
              throw new UsernameNotFoundException("Invalid credentials");
          }
    }

//    @GetMapping("/validate")
//    public String validateToken(@RequestParam String token){
//         authService.ValidateToken(token);
//         return "Token is valid";
//    }
}
