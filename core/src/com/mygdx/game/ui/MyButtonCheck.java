package com.mygdx.game.ui;

import java.util.LinkedList;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Game;

public class MyButtonCheck extends Button {
    LinkedList<MyButtonCheck> disable = new LinkedList<MyButtonCheck>();

    public MyButtonCheck(int x, int y, String texture_up, String texture_checked, boolean startChecked,
            final MyListener listener) {
        Drawable up = Game.skin.getDrawable(texture_up);
        Drawable checked = Game.skin.getDrawable(texture_checked);

        setBounds(x, y, up.getMinWidth(), up.getMinHeight());
        ButtonStyle style = new ButtonStyle();
        style.checked = checked;
        style.up = up;
        setChecked(startChecked);
        setStyle(style);
        InputListener inputListener = new InputListener() {
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer,
                    int button) {
                return true;
            }

            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer,
                    int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listener.onClick();
                    for (MyButtonCheck other : disable) {
                        other.setChecked(false);
                    }
                }
            }
        };
        addListener(inputListener);
    }
}
