package com.ioc.beans.autoManaged;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AutoGameOne implements AutoGame {
    @Override
    public String getGame() {
        return "Game One";
    }

    @Override
    public String toString() {
        return "Game One";
    }
}
