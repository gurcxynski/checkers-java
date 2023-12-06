package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class StartMenu extends Menu {
    public StartMenu() {
        addActor(new MyTextButton(100, 550, "NEW GAME", new MyListener() {
            public void onClick() { Game.machine.toMenu(NewGameMenu.class); }
        }));
        addActor(new MyTextButton(100, 300, "OPTIONS", new MyListener() {
            public void onClick() { Game.machine.toMenu(OptionsMenu.class); }
        }));
        addActor(new MyTextButton(100, 50, "QUIT", new MyListener() {
            public void onClick() { Gdx.app.exit(); }
        }));
    }
}
