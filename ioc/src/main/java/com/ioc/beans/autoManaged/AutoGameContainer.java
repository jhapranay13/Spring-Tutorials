package com.ioc.beans.autoManaged;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AutoGameContainer {
    private AutoGame game;

    // No need to autowire in case of constructor injection
    public AutoGameContainer(@Qualifier("AutoGameTwo") AutoGame game) {
        this.game = game;
    }

    public String toString() {
        return "container " + game;
    }
}
