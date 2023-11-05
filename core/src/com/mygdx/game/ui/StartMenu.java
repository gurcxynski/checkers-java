package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;

public class StartMenu extends Menu {
    
    public StartMenu() {
        addActor(new MyButton(100, 550, "playsolo", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    MyGdxGame.machine.newLocalGame();
                }
            }
        }));
        addActor(new MyButton(100, 300, "playonline", "light_tile",new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    MyGdxGame.machine.toOnlineMenu();
                }
            }
        }));
        addActor(new MyButton(100, 50, "quit", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    Gdx.app.exit();
                }
            }
        }));
    }
}
