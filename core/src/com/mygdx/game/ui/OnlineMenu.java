package com.mygdx.game.ui;

import com.mygdx.game.Game;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        addActor(new MyTextButton(2, "HOST", new MyListener() {
	        public void onClick() { Game.machine.toMenu(HostMenu.class);}
        }));
        addActor(new MyTextButton(1, "JOIN", new MyListener() {
	        public void onClick() { Game.machine.toMenu(JoinMenu.class); }
        }));
        addActor(new MyTextButton(0, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(NewGameMenu.class); }
        }));
    }
}
