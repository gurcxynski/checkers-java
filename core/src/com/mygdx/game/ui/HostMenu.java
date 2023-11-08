package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class HostMenu extends Menu {
    public HostMenu() {
        final MyButtonCheck white = new MyButtonCheck(100, 650, "white_unchecked", "white_checked", true, new MyListener());
        final MyButtonCheck black = new MyButtonCheck(300, 650, "black_unchecked", "black_checked", false, new MyListener());

        white.disable.add(black);
        black.disable.add(white);

        addActor(white);
        addActor(black);
        addActor(new MyButton(100, 350, "host", "light_tile", new MyListener() {
            public void onClick() { Game.machine.hostOnlineGame(white.isChecked()); }
        }));
        addActor(new MyButton(100, 50, "back", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new OnlineMenu()); }
        }));
    }
}
