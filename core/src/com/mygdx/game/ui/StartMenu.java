package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class StartMenu extends Menu {
    public StartMenu() {
        super();

        super.addTextButton(new MyTextButton("NEW GAME", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(NewGameMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);
        super.addTextButton(new MyTextButton("OPTIONS", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OptionsMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);

        super.addTextButton(new MyTextButton("QUIT", new MyListener() {
            public void onClick() {
                Gdx.app.exit();
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);

        addActor(super.table);
    }
}
