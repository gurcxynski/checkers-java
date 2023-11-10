package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Game;

public class MyButton extends Button {
    public  MyButton(int x, int y, String texture_up, String texture_down, final MyListener listener) {
        Drawable up = Game.skin.getDrawable(texture_up);
        Drawable down = Game.skin.getDrawable(texture_down);
        setBounds(x, y, up.getMinWidth(), up.getMinHeight());
        ButtonStyle style = new ButtonStyle();
        style.down = down;
        style.up = up;
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
        setStyle(style);
        addListener(inputListener);
    }
}
