package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class StartMenu extends Menu {
        public StartMenu() {
        addActor(new MyButton(100, 550, "newgame", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new NewGameMenu());}
        }));
        addActor(new MyButton(100, 300, "options", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(new OptionsMenu()); }
        }));
        addActor(new MyButton(100, 50, "quit", "light_tile", new MyListener() {
	        public void onClick() { Gdx.app.exit(); }
        }));
    }
}
