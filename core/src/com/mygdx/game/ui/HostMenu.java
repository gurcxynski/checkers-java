package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.Game;

public class HostMenu extends Menu {    
    boolean white_selected = true;
    public HostMenu() {
        addActor(new MyButtonCheck(100, 650, "white_unchecked", "white_checked", white_selected, new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    for (Actor actor : Game.machine.activeStage.getActors()) {
                        if (actor instanceof MyButtonCheck) {
                            ((MyButtonCheck) actor).setChecked(false);
                        }
                    }
                    ((MyButtonCheck) event.getTarget()).setChecked(true);
                    }
                    white_selected = true;
                }
        }));
        addActor(new MyButtonCheck(300, 650, "black_unchecked", "black_checked", !white_selected, new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    for (Actor actor : Game.machine.activeStage.getActors()) {
                        if (actor instanceof MyButtonCheck) {
                            ((MyButtonCheck) actor).setChecked(false);
                        }
                    }
                    ((MyButtonCheck) event.getTarget()).setChecked(true);
                    }
                    white_selected = false;
                }
        }));
        addActor(new MyButton(100, 350, "host", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    Game.machine.hostOnlineGame(white_selected);
                }
            }
        }));
        addActor(new MyButton(100, 50, "back", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    Game.machine.toStartMenu();
                }
            }
        }));
    }
}
