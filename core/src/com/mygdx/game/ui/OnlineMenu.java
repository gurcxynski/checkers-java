package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        addActor(new MyButton(100, 550, "white_button", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    MyGdxGame.machine.hostOnlineGame(true);
                }
            }
        }));
        addActor(new MyButton(100, 300, "black_button", "light_tile",new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    MyGdxGame.machine.hostOnlineGame(false);
                }
            }
        }));
        addActor(new MyButton(100, 50, "join", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    MyGdxGame.machine.joinOnlineGame();
                }
            }
        }));
    }
}
