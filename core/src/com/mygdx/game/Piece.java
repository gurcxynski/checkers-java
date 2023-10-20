package com.mygdx.game;
public class Piece {
    private boolean isKing;
    private boolean white;
    private int x;
    private int y;

    public Piece(int x, int y, boolean white, boolean isKing) {
        this.isKing = isKing;
        this.white = white;
        this.x = x;
        this.y = y;
    }

    public boolean isKing() {
        return isKing;
    }

    public boolean isWhite() {
        return white;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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
    public void moveBy(int x, int y) {
        moveTo(this.x + x, this.y + y);
    }
    public void remove() {
        x = -1;
        y = -1;
    }
}