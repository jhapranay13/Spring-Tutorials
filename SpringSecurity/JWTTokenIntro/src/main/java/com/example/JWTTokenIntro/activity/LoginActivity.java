package com.example.JWTTokenIntro.activity;

import com.example.JWTTokenIntro.model.LoginRequest;
import com.example.JWTTokenIntro.model.LoginResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginActivity {
    AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String jwt = null;
        log.info("LoginRequest >> {} {}", loginRequest.userName(), loginRequest.password());

        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.userName(),
                loginRequest.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        log.info("Authentication response >> {}", authenticationResponse.getName());
        log.info("Authentication response >> {} {}", authentication.getDetails());

        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
            String secret = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            jwt = Jwts.builder().issuer("my test").subject("JWT TOKEN").
                    claim("username", authenticationResponse.getName()).
                    claim("authorities", authenticationResponse.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
                    .issuedAt(new java.util.Date())
                    .expiration(new java.util.Date(System.currentTimeMillis() + 600000))
                    .signWith(secretKey).compact();
        }
        return ResponseEntity.status(HttpStatus.OK)
                        .header("Authorization", jwt)
                .body(new LoginResponse(HttpStatus.OK.getReasonPhrase(), jwt));
    }
}
