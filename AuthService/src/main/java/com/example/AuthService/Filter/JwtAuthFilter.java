package com.example.AuthService.Filter;

import com.example.AuthService.Services.JWTService;
import com.example.AuthService.Services.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailService UserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws java.io.IOException, jakarta.servlet.ServletException {
        // Implement your JWT authentication logic here
        String token=null;
        String username=null;
        // For example, you can extract the token from the Authorization header,
        // validate it, and set the authentication in the security context if valid.
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
             token = authHeader.substring(7);
            // Validate the token and set authentication in the security context
            // You can use a JWT utility class to validate the token and extract user details

            // If the token is valid, set the authentication in the security context
            username=jwtService.ExtractUsername(token);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            // Set the authentication in the security context
            // You can create an authentication object and set it in the context
            // For example, you can use UsernamePasswordAuthenticationToken for this
            // Remember to set the authorities/roles as well if needed
           UserDetails userdetail=UserDetailService.loadUserByUsername(username);
          if(jwtService.validateToken( token,  userdetail)){
                // Create an authentication object and set it in the context
               // You can use UsernamePasswordAuthenticationToken for this
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetail, null, userdetail.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // After processing the JWT, continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
