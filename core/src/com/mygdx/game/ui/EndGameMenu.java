package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Game;

public class EndGameMenu extends Stage {
    public EndGameMenu(boolean winner) {
        addActor(new MyButton(250, 350, (winner ? "white" : "black") + "_won", "light_tile", new MyListener() {
	        public void onClick() { Game.machine.toMenu(NewGameMenu.class); }
        }));
    }
}
