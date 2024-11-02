package com.ioc.beans.autoManaged;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("AutoGameTwo")
public class AutoGameTwo implements AutoGame {
    @Override
    public String getGame() {
        return "Game Two";
    }

    @Override
    public String toString() {
        return "Game Two";
    }
}
