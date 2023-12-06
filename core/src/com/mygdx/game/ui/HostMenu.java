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
        addActor(new MyTextButton(100, 350, "HOST", new MyListener() {
            public void onClick() { Game.machine.hostOnlineGame(white.isChecked()); }
        }));
        addActor(new MyTextButton(100, 50, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(OnlineMenu.class); }
        }));
    }
}
