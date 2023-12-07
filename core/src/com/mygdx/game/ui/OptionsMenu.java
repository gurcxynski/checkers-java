package com.mygdx.game.ui;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class OptionsMenu extends Menu {
    public OptionsMenu() {
        LinkedList<MyButtonCheck> buttons = new LinkedList<MyButtonCheck>();
        int n = Gdx.files.internal("assets/skins/").list().length;
        for (int i = 1; i <= n; i++) {
            final int a = i;
            buttons.add(new MyButtonCheck(
                (WindowConfig.INSIDE_SQUARE / n) * (i - 1) + (int)((WindowConfig.INSIDE_SQUARE / n) * 0.1), 
                (int)(WindowConfig.INSIDE_SQUARE * 0.6), 
                (int)((WindowConfig.INSIDE_SQUARE / n) * 0.8), 
                (int)((WindowConfig.INSIDE_SQUARE / n) * 0.8), 
                "chessboard" + i, "overlay", i == 1,
                    new MyListener() {
                        public void onClick() {
                            Game.style = a;
                        }
                    }));
        }
        for (MyButtonCheck button : buttons) {
            for (MyButtonCheck other : buttons) {
                if (button != other) {
                    button.disable.add(other);
                }
            }
            addActor(button);
        }
        addActor(new MyTextButton(0, "BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        }));
    }
}
