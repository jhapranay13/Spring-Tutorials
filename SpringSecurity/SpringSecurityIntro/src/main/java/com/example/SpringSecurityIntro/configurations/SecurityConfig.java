package com.example.SpringSecurityIntro.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().permitAll());
        //httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().denyAll());

        httpSecurity.authorizeHttpRequests((request) -> request
                .requestMatchers("/account", "/balance", "/cards", "/loans").authenticated()// these are authenticaed
                .requestMatchers("/notices", "/contacts", "/error").permitAll());// All can access
        httpSecurity.formLogin(Customizer.withDefaults());
        // disablig auth
        /* httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> {
             httpSecurityFormLoginConfigurer.disable();
        });*/
        httpSecurity.httpBasic(Customizer.withDefaults());
       /* httpSecurity.httpBasic(httpSecurityHttpBasicConfigurer -> {
            httpSecurityHttpBasicConfigurer.disable();
        });*/
        return httpSecurity.build();
    }

    // Onlu Provides functionality to load the user.
    // does not provide create user, change password etc.
    @Bean
    public UserDetailsService loadUserDetailsService() {
        UserDetails userDetails = User.withUsername("user")
                .password("{noop}12345") // if encoders are not configures {noop}
                .authorities("read")
                .build();

        UserDetails adminDetails = User.withUsername("admin")
                .password("{bcrypt}$2a$12$EwrU0vfr.t5KNrWTYDP2Ce2pbuPjgysx9ndWTgsjILpXFnl6aWi3C") // BCrypt Encoded
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(adminDetails, userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // in order to check for compromised password. introduced in spring 6.3
   /* @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/
}
