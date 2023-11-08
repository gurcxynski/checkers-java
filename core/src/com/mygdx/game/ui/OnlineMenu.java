package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        addActor(new MyButton(100, 550, "host", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new HostMenu());}
        }));
        addActor(new MyButton(100, 300, "join", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new JoinMenu()); }
        }));
        addActor(new MyButton(100, 50, "back", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new NewGameMenu()); }
        }));
    }
}
