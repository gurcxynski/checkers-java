package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {
    private boolean isKing;
    private boolean white;
    private int x;
    private int y;

    public Piece(int x, int y, boolean white) {
        this.white = white;
        isKing = false;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Batch batch, float p_alpha) {
        int size = WindowConfig.SIZE;
        Texture texture = Game.skin
                .get(isKing ? (white ? "whiteking" : "blackking") : (white ? "white" : "black"), Texture.class);
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
        isKing = true;
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