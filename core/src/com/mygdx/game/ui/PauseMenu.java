package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class PauseMenu extends Menu {
    public PauseMenu() {
        addActor(new MyTextButton(3, "NEW GAME", new MyListener() {
            public void onClick() { Game.machine.toMenu(NewGameMenu.class); }
        }));
        addActor(new MyTextButton(2, "QUIT", new MyListener() {
            public void onClick() { Gdx.app.exit(); }
        }));
        addActor(new MyTextButton(0, "BACK", new MyListener() {
            public void onClick() { Game.machine.resume(); }
        }));
    }
}
