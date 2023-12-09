package com.mygdx.game.ui;

import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class PauseMenu extends Menu {
    public PauseMenu() {
        super(false);

        addActor(new MyButton(
                (int) (WindowConfig.OUTSIDE_SQUARE * 0.9 - WindowConfig.MARGIN),
                (int) (WindowConfig.OUTSIDE_SQUARE * 0.9 - WindowConfig.MARGIN),
                (WindowConfig.OUTSIDE_SQUARE / 10), "settings_icon_transparent",
                new MyListener() {
                    public void onClick() {
                        Game.machine.resume();
                    }
                }));

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
