package com.ioc.beans.userManaged;

public class GameContainer {
    private Game game;

    public GameContainer(Game game) {
        this.game = game;
    }

    public String toString() {
        return "container " + game;
    }
}
