package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Button {
    int X;
    int Y;
    Texture texture;

    public Button(int x, int y, Texture texture) {
        X = x;
        Y = y;
        this.texture = texture;
    }

    public void draw(Batch batch) {
        batch.draw(texture, X, 800 - texture.getHeight() - Y);
    }

    boolean hovered() {
        return Gdx.input.getX() > X && Gdx.input.getX() < X + texture.getWidth() && Gdx.input.getY() > Y
                && Gdx.input.getY() < Y + texture.getHeight();
    }

    public void update() {
        if (hovered() && Gdx.input.justTouched()) {
            if (texture == Globals.textures.get("play_solo")) {
                Globals.machine.newLocalGame();
            }
            if (texture == Globals.textures.get("play_multi")) {
                Globals.machine.toOnlineMenu();
            }
            if (texture == Globals.textures.get("quit")) {
                Gdx.app.exit();
            }
            if (texture == Globals.textures.get("join")) {
                Globals.machine.newOnlineGame(false, false);
            }
            if (texture == Globals.textures.get("button_white")) {
                Globals.machine.newOnlineGame(true, true);
            }
            if (texture == Globals.textures.get("button_black")) {
                Globals.machine.newOnlineGame(false, true);
            }
        }
    }
}
