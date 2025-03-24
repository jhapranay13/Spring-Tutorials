package com.example.JWTTokenIntro.configurations;

import com.example.JWTTokenIntro.model.Customer;
import com.example.JWTTokenIntro.repository.CustomerRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("default")  // it's used to activate a bean for a specific profile
public class CustomUserDetailService implements UserDetailsService {

    private final CustomerRepo customerRepo;

    public CustomUserDetailService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for username: " + username + "."));
        List<GrantedAuthority> listAuthority = customer.getAuthorities()
                .stream()
                .map(authority ->
                        new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new User(customer.getEmail(), customer.getPwd(), listAuthority);
    }
}
