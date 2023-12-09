package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class PauseMenu extends Menu {
    public PauseMenu() {
        super();

        super.addTextButton(new MyTextButton("NEW GAME", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(NewGameMenu.class);
            }
        }));
        super.addTextButton(new MyTextButton("QUIT", new MyListener() {
            public void onClick() {
                Gdx.app.exit();
            }
        }));

        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.resume();
            }
        }));

        addActor(super.table);
    }
}
