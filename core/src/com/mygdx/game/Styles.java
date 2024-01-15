package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
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
		buttonStyle.checked = Game.skin.getDrawable("button" + styles[n] + "checked");
		buttonStyle.font = Game.skin.getFont("font");
        return buttonStyle;
    }
    public static Color getBackgroundColor(int n) {
        switch (n - 1) {
            case 0:
                return new Color(
                    0.45098039215686275f,
                    0.4666666666666667f,
                    0.5215686274509804f, 
                    1);
            case 1:
                return new Color(
                    0.7176470588235294f,
                    0.5686274509803921f,
                    0.41568627450980394f, 
                    1);
            case 2:
                return new Color(
                    1f,
                    1f,
                    1f, 
                    1);
        }
        return null;
    }
}
