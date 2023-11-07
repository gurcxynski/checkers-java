package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Game;

public class EndGameMenu extends Stage {
    public EndGameMenu(boolean winner) {
        addActor(new MyButton(250, 350, (winner ? "white" : "black") + "_won", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    Game.machine.toMenu(new StartMenu());
                }
            }
        }));
    }
}
