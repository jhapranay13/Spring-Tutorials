package com.example.JWTTokenIntro.configurations;

import com.example.JWTTokenIntro.authprovider.CustomAuthProvider;
import com.example.JWTTokenIntro.exceptionHandling.CustomAccessDeniedHandler;
import com.example.JWTTokenIntro.exceptionHandling.CustomBasicEntryPoint;
import com.example.JWTTokenIntro.filter.CustomCsrfCookieFilter;
import com.example.JWTTokenIntro.filter.JWTAuthGenerationFilter;
import com.example.JWTTokenIntro.filter.JWTTokenValidationFilter;
import com.example.JWTTokenIntro.filter.RequestValidationBeforeFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Collections.singletonList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers( "/contact","/register", "/login")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CustomCsrfCookieFilter(), BasicAuthenticationFilter.class)  // will execute after basic authentication
                .addFilterAfter(new JWTAuthGenerationFilter(), BasicAuthenticationFilter.class)  // will execute after basic authentication
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)  // will execute after basic authentication
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class) // will execute before basic authentication
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Only HTTP
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/account").hasRole("USER")
                        .requestMatchers("/balance").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/loans").hasRole("USER")
                        .requestMatchers("/cards").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession", "/login").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic((httpSecurityHttpBasicConfigurer) -> {
                    httpSecurityHttpBasicConfigurer.authenticationEntryPoint(new CustomBasicEntryPoint());});
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }


    // Default authentication Provider is DAOAuthenticationProvider
    // When we get UserNameAndPasswordToken the above provider gets activated
    // Don't need this as we have custom Implementation
/*    @Bean
    public UserDetailsService loadUserDetailsService(DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // in order to check for compromised password. introduced in spring 6.3
   /* @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/

    // For Jwt
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        CustomAuthProvider authProvider = new CustomAuthProvider(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}
