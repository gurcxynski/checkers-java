package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class OptionsMenu extends Menu {
    public OptionsMenu() {
        addActor(new MyButton(100, 50, "back", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new NewGameMenu()); }
        }));
    }
}
