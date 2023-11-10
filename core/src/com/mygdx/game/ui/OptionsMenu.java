package com.mygdx.game.ui;

import java.util.LinkedList;

import com.mygdx.game.Game;

public class OptionsMenu extends Menu {
    public OptionsMenu() {
        LinkedList<MyButtonCheck> buttons = new LinkedList<MyButtonCheck>();
        for (int i = 1; i <= 8; i++) {
            final int a = i;
            buttons.add(new MyButtonCheck((i % 3) * 200 + 150, (i / 3) * 200 + 300, "chessboard" + i, "overlay", i == 1, new MyListener() {
                public void onClick() { Game.style = a; }
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
        addActor(new MyButton(100, 50, "back", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(NewGameMenu.class); }
        }));
    }
}
