package com.example.AuthorizationAndAuthentication.eventListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthoriztionEvents {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent event) {
        log.info("Authorization Denies for {} due to {}", event.getAuthentication().get().getName(),
                event.getAuthorizationDecision().toString());
    }
}
