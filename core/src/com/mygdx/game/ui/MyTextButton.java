package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class MyTextButton extends TextButton {
    public MyTextButton(int tier, String text, final MyListener listener) {
        super(text, Game.skin);
        setBounds(WindowConfig.BUTTON_DEFAULT_X + WindowConfig.MARGIN, (WindowConfig.BUTTON_DEFAULT_STEP * tier) + WindowConfig.MARGIN, WindowConfig.BUTTON_DEFAULT_WIDTH, WindowConfig.BUTTON_DEFAULT_HEIGHT);
        
        InputListener inputListener = new InputListener() {
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listener.onClick();
                }
            }
        };
        addListener(inputListener);
    }
    public MyTextButton(int x, int y, String text, final MyListener listener) {
        super(text, Game.skin);
        setBounds(x + WindowConfig.MARGIN, y + WindowConfig.MARGIN, WindowConfig.BUTTON_DEFAULT_WIDTH, WindowConfig.BUTTON_DEFAULT_HEIGHT);
        
        InputListener inputListener = new InputListener() {
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listener.onClick();
                }
            }
        };
        addListener(inputListener);
    }   
    public MyTextButton(int x, int y, int width, int height, String text, final MyListener listener) {
        super(text, Game.skin);
        setBounds(x + WindowConfig.MARGIN, y + WindowConfig.MARGIN, width, height);
        
        InputListener inputListener = new InputListener() {
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listener.onClick();
                }
            }
        };
        addListener(inputListener);
    }   
}
