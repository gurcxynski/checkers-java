package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class NewGameMenu extends Menu {
    public NewGameMenu() {
        addActor(new MyTextButton(100, 550, "PLAY LOCAL", new MyListener() {
	        public void onClick() { Game.machine.newLocalGame();}
        }));
        addActor(new MyTextButton(100, 300, "PLAY ONLINE", new MyListener() {
	        public void onClick() { Game.machine.toMenu(OnlineMenu.class); }
        }));
        addActor(new MyTextButton(100, 50, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(StartMenu.class); }
        }));
    }
}
