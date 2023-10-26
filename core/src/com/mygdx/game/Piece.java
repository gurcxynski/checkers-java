package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {
    private boolean isKing;
    private boolean white;
    private int x;
    private int y;

    public Piece(int x, int y, boolean white, boolean isKing)  {
        this.isKing = isKing;
        this.white = white;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void draw(Batch batch, float p_alpha) {
        batch.draw(white ? Globals.textures.get("white") : Globals.textures.get("red"), 
        (!Globals.machine.playingWhite ? 420 -  x * 60 : x * 60), (!Globals.machine.playingWhite ? 420 - y * 60 : y * 60), 60, 60);
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
    public void moveBy(int x, int y) {
        moveTo(this.x + x, this.y + y);
    }
    
    public void hide() {
        x = -1;
        y = -1;
    }
}