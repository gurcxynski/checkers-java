package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public final class Styles {
    public static String[] styles = { "gray", "wooden", "black and white" };
    public static TextButtonStyle[] buttonStyles = new TextButtonStyle[styles.length];
    public static void loadStyles() {
        for (int i = 0; i < styles.length; i++) {
            buttonStyles[i] = buttonStyleFactory(i);
        }
    }
    private static TextButtonStyle buttonStyleFactory(int n) {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.up = Game.skin.getDrawable("button" + styles[n] + "up");
		buttonStyle.down = Game.skin.getDrawable("button" + styles[n] + "down");
		//buttonStyle.checked = Game.skin.getDrawable("button" + styles[n] + "checked");
		buttonStyle.font = Game.skin.getFont("font");
        return buttonStyle;
    }
}
