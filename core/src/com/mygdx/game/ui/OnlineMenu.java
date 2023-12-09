package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class OnlineMenu extends Menu {
    public OnlineMenu() {
        super.addTextButton(new MyTextButton("HOST", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(HostMenu.class);
            }
        }));
        super.addTextButton(new MyTextButton("JOIN", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(JoinMenu.class);
            }
        }));
        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(NewGameMenu.class);
            }
        }));

        addActor(super.table);
    }
}
