package com.mygdx.game;
public class Piece {
    private boolean isKing;
    private boolean isRed;
    private int x;
    private int y;

    public Piece(boolean isKing, boolean isRed, int x, int y) {
        this.isKing = isKing;
        this.isRed = isRed;
        this.x = x;
        this.y = y;
    }

    public boolean isKing() {
        return isKing;
    }

    public boolean isRed() {
        return isRed;
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

    public void move(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }
        this.x = x;
        this.y = y;
    }

    public void remove() {
        x = -1;
        y = -1;
    }
}