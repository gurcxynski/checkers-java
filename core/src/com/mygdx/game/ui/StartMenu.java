package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class StartMenu extends Menu {
    
    public StartMenu() {
        addActor(new MyButton(100, 550, "playsolo", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.newLocalGame();}
        }));
        addActor(new MyButton(100, 300, "playonline", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new OnlineMenu()); }
        }));
        addActor(new MyButton(100, 50, "back", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new StartMenu()); }
        }));
    }
}
