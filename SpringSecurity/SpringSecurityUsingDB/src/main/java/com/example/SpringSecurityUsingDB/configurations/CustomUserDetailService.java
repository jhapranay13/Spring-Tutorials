package com.example.SpringSecurityUsingDB.configurations;

import com.example.SpringSecurityUsingDB.model.Customer;
import com.example.SpringSecurityUsingDB.repository.CustomerRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<GrantedAuthority> gList = List.of(new SimpleGrantedAuthority(customer.getRole()));

        return new User(customer.getEmail(), customer.getPwd(), gList);
    }
}
