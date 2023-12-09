package com.mygdx.game.ui;

import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class OnlineMenu extends Menu {
    public OnlineMenu() {
        super.addTextButton(new MyTextButton("HOST", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(HostMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);
        super.addTextButton(new MyTextButton("JOIN", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(JoinMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);
        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(NewGameMenu.class);
            }
        }), 20, WindowConfig.BUTTON_DEFAULT_WIDTH);

        addActor(super.table);
    }
}
