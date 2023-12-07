package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class NewGameMenu extends Menu {
    public NewGameMenu() {
        addActor(new MyTextButton(2, "PLAY LOCAL", new MyListener() {
	        public void onClick() { Game.machine.newLocalGame();}
        }));
        addActor(new MyTextButton(1, "PLAY ONLINE", new MyListener() {
	        public void onClick() { Game.machine.toMenu(OnlineMenu.class); }
        }));
        addActor(new MyTextButton(0, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(StartMenu.class); }
        }));
    }
}
