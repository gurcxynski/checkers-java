package com.mygdx.game.ui;

import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class NewGameMenu extends Menu {

    public NewGameMenu() {
        super();

        super.addTextButton(new MyTextButton("PLAY LOCAL", new MyListener() {
            public void onClick() {
                Game.machine.newLocalGame();
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);

        super.addTextButton(new MyTextButton("PLAY ONLINE", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OnlineMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);

        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);

        addActor(super.table);
    }
}
