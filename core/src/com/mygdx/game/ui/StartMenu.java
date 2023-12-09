package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class StartMenu extends Menu {
    public StartMenu() {
        super();

        super.addTextButton(new MyTextButton("NEW GAME", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(NewGameMenu.class);
            }
        }));
        super.addTextButton(new MyTextButton("OPTIONS", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OptionsMenu.class);
            }
        }));

        super.addTextButton(new MyTextButton("QUIT", new MyListener() {
            public void onClick() {
                Gdx.app.exit();
            }
        }));

        addActor(super.table);
    }
}
