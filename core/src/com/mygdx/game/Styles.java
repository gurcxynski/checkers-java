package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
    public static Texture getPieceTexture(boolean white) {
        String textureName = styles[Game.style] + "_" + (white ? "white" : "black") + "_piece";
        return Game.skin.get(textureName, Texture.class);
    }
    public static Texture getKingTexture(boolean white) {
        String textureName = styles[Game.style] + "_" + (white ? "white" : "black") + "_king";
        return Game.skin.get(textureName, Texture.class);
    }
    public static Texture getBoardTexture() {
        String textureName = styles[Game.style] + "_board";
        return Game.skin.get(textureName, Texture.class);
    }
    private static TextButtonStyle buttonStyleFactory(int n) {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.up = Game.skin.getDrawable(styles[n] + "_buttonup");
		buttonStyle.down = Game.skin.getDrawable(styles[n] + "_buttondown");
		buttonStyle.checked = Game.skin.getDrawable(styles[n] + "_buttonchecked");
		buttonStyle.font = Game.skin.getFont("font");
        return buttonStyle;
    }
    public static Color getBackgroundColor(int n) {
        switch (n) {
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
                    0.7019607843137254f,
                    0.7019607843137254f,
                    0.7019607843137254f, 
                    1);
        }
        return null;
    }
}
