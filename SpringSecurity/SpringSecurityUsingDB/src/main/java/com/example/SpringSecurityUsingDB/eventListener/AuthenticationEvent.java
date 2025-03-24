package com.example.SpringSecurityUsingDB.eventListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationEvent {

    @EventListener
    public void onSuccessfulAuthentication(AuthenticationSuccessEvent event) {
        log.info("Authentication Success: " + event.getAuthentication().getName());
    }

    @EventListener
    public void onFailedAuthentication(AbstractAuthenticationFailureEvent event) {
        log.error("Authentication Failed: {}  {}", event.getAuthentication().getName(), event.getException().getMessage());
    }
}
