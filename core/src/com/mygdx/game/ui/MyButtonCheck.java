package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Game;

public class MyButtonCheck extends Button {
    public MyButtonCheck(int x, int y, String texture_up, String texture_checked, boolean startChecked, InputListener listener) {
        Drawable up = Game.skin.getDrawable(texture_up);
        Drawable checked = Game.skin.getDrawable(texture_checked);
        setBounds(x, y, up.getMinWidth(), up.getMinHeight());
        ButtonStyle style = new ButtonStyle();
        style.checked = checked;
        style.up = up;
        setChecked(startChecked);
        setStyle(style);
        addListener(listener);
    }
}
