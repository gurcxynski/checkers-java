package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        addActor(new MyTextButton(100, 550, "HOST", new MyListener() {
	        public void onClick() { Game.machine.toMenu(HostMenu.class);}
        }));
        addActor(new MyTextButton(100, 300, "JOIN", new MyListener() {
	        public void onClick() { Game.machine.toMenu(JoinMenu.class); }
        }));
        addActor(new MyTextButton(100, 50, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(NewGameMenu.class); }
        }));
    }
}
