package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {
    private boolean isKing;
    private boolean white;
    private int x;
    private int y;
    private Texture texture;
    public Piece(int x, int y, boolean white) {
        this.white = white;
        isKing = false;
        this.x = x;
        this.y = y;
        texture = Game.skin.get(white ? "white" : (Game.style == 2 ? "red" : "black"), Texture.class);
    }

    @Override
    public void draw(Batch batch, float p_alpha) {
        int size = WindowConfig.SIZE;
        batch.draw(texture, Game.machine.drawBlackDown() ? (7 * size) - (x * size - WindowConfig.MARGIN) : x * size + WindowConfig.MARGIN,
                Game.machine.drawBlackDown() ? (7 * size) - (y * size - WindowConfig.MARGIN) : y * size + WindowConfig.MARGIN, size, size);
    }

    public int[] getGrid() {
        return new int[] { x, y };
    }

    public boolean isKing() {
        return isKing;
    }

    public boolean isWhite() {
        return white;
    }

    public int GridX() {
        return x;
    }

    public int GridY() {
        return y;
    }

    public void kingMe() {
        texture = Game.skin.get(white ? "white_king" : (Game.style == 2 ? "red_king" : "black_king"), Texture.class);
        isKing = true;
    }

    public void updateColor() {
        texture = Game.skin.get(white ? "white" : (Game.style == 2 ? "red" : "black"), Texture.class);
        if (isKing) texture = Game.skin.get(white ? "white_king" : (Game.style == 2 ? "red_king" : "black_king"), Texture.class);
    }

    public void moveTo(int x, int y) {
        if (!Helpers.inBoard(new int[] { x, y })) {
            return;
        }
        this.x = x;
        this.y = y;
    }

    public void moveTo(int[] field) {
        moveTo(field[0], field[1]);
    }

    public void moveBy(int x, int y) {
        moveTo(this.x + x, this.y + y);
    }

    public void hide() {
        x = -1;
        y = -1;
    }

    public String getFieldString() {
        return "" + (char) (x + 97) + (char) (y + 49);
    }

    public int[] getField() {
        return new int[] { x, y };
    }
}