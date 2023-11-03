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
        Texture texture = Globals.textures
                .get(isKing ? (white ? "whiteking" : "blackking") : (white ? "white" : "black"));
        batch.draw(texture, (Globals.machine.drawBlackDown() ? 700 - (x * 100) : x * 100),
                (Globals.machine.drawBlackDown() ? 700 - (y * 100) : y * 100), 100, 100);
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
        if (x < 0 || x > 7 || y < 0 || y > 7) {
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