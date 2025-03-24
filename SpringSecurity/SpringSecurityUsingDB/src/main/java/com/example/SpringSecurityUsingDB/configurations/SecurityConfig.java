package com.example.SpringSecurityUsingDB.configurations;

import com.example.SpringSecurityUsingDB.exceptionHandling.CustomAccessDeniedHandler;
import com.example.SpringSecurityUsingDB.exceptionHandling.CustomBasicEntryPoint;
import com.example.SpringSecurityUsingDB.filter.CustomCsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().permitAll());
        //httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().denyAll());
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();  // To send the token through header. Cookie will be attatched by browser
        httpSecurity
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(List.of("http://localhost:3000"));
                            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                            cors.setAllowCredentials(true);
                            //cors.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
                            cors.setAllowedHeaders(List.of("*"));

                            return cors;
                        })
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionFixation().changeSessionId() // To change the session id. Caontians new session and migrate session too
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Always create a session
                        .invalidSessionUrl("/invalidSession")
                        .maximumSessions(1)    // Number of Concurrent sessionc
                        .maxSessionsPreventsLogin(true) // If true, it will not allow to login if max session is reached
                        .expiredUrl("/invalidSession") // If session is expired
                )
               // .requiresChannel(httpSecurityRequiresChannelConfigurer -> httpSecurityRequiresChannelConfigurer.anyRequest().requiresSecure()) // For HTTPS
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler) // To send the token through header. Cookie will be attatched by browser
                        .ignoringRequestMatchers("/contacts", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // For CSRF if not set to false Javascript cannot read the cookie and send it back
                .addFilterAfter(new CustomCsrfCookieFilter(), BasicAuthenticationFilter.class)  // will execute after basic authentication
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/account", "/balance", "/cards", "/loans").authenticated()// these are authenticaed
                .requestMatchers("/notices", "/contacts", "/error", "/user", "/invalidSession", "/register").permitAll());// All can access

        httpSecurity.httpBasic((httpSecurityHttpBasicConfigurer) -> {
            httpSecurityHttpBasicConfigurer.authenticationEntryPoint(new CustomBasicEntryPoint()); // For Error Handling in case authentication fails
        }).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID"));

        httpSecurity.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(new CustomAccessDeniedHandler());
                  //  .accessDeniedPage("/error");  // optional
        });
        /*// Another way to configure the above at a global scope
        httpSecurity.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new CustomBasicEntryPoint());
        });*/
       /* httpSecurity.httpBasic(httpSecurityHttpBasicConfigurer -> {
            httpSecurityHttpBasicConfigurer.disable();
        });*/

        httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/account") // landing page after login successfull
                    .failureUrl("/error")
                    .usernameParameter("username") // HTML will look for these parameters
                    .passwordParameter("password") // in HTML form the names should be same
                    .permitAll();
        });
        // disablig auth
        /* httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> {
             httpSecurityFormLoginConfigurer.disable();
        });*/
        return httpSecurity.build();
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
}
