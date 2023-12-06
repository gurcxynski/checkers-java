package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Game;

public class MyTextButton extends TextButton {
    public MyTextButton(int x, int y, String text, final MyListener listener) {
        super(text, Game.skin);
        setBounds(x, y, getBackgroundDrawable().getMinWidth(), getBackgroundDrawable().getMinHeight());
        
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
