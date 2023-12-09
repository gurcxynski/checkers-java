package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class PauseMenu extends Menu {
    public PauseMenu() {
        super(false);

        super.addTextButton(new MyTextButton("EXIT TO MENU", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
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
