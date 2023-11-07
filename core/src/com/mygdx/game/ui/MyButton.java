package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Game;

public class MyButton extends Button {
    public MyButton(int x, int y, String texture_up, String texture_down, InputListener listener) {
        Drawable up = Game.skin.getDrawable(texture_up);
        Drawable down = Game.skin.getDrawable(texture_down);
        setBounds(x, y, up.getMinWidth(), up.getMinHeight());
        ButtonStyle style = new ButtonStyle();
        style.down = down;
        style.up = up;
        setStyle(style);
        addListener(listener);
    }
}
