package com.mygdx.game.ui;

import com.mygdx.game.Game;
public class NewGameMenu extends Menu {

    public NewGameMenu() {
        super();

        super.addTextButton(new MyTextButton("PLAY LOCAL", new MyListener() {
            public void onClick() {
                Game.machine.newLocalGame();
            }
        }));

        super.addTextButton(new MyTextButton("PLAY ONLINE", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OnlineMenu.class);
            }
        }));

        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        }));

        addActor(super.table);
    }
}
