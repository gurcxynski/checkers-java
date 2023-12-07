package com.mygdx.game.ui;

import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class HostMenu extends Menu {
    public HostMenu() {
        final MyButtonCheck white = new MyButtonCheck(WindowConfig.BUTTON_DEFAULT_X, WindowConfig.BUTTON_DEFAULT_STEP, (int)(WindowConfig.INSIDE_SQUARE * 0.2), (int)(WindowConfig.INSIDE_SQUARE * 0.2), "white_unchecked", "white_checked", true, new MyListener());
        final MyButtonCheck black = new MyButtonCheck(WindowConfig.BUTTON_DEFAULT_X + (int)(WindowConfig.INSIDE_SQUARE * 0.2) + WindowConfig.MARGIN, WindowConfig.BUTTON_DEFAULT_STEP, (int)(WindowConfig.INSIDE_SQUARE * 0.2), (int)(WindowConfig.INSIDE_SQUARE * 0.2), "black_unchecked", "black_checked", false, new MyListener());

        white.disable.add(black);
        black.disable.add(white);

        addActor(white);
        addActor(black);
        addActor(new MyTextButton(1, "HOST", new MyListener() {
            public void onClick() { Game.machine.hostOnlineGame(white.isChecked()); }
        }));
        addActor(new MyTextButton(0, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(OnlineMenu.class); }
        }));
    }
}
