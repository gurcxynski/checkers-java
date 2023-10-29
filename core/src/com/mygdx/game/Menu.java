package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Menu {
    ArrayList<Button> buttons = new ArrayList<Button>();

    public void draw(Batch batch) {
        batch.begin();
        batch.draw(Globals.textures.get("menu"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(Button button : buttons) {
            button.draw(batch);
        }
        batch.end();
    }

    public boolean update() {
        for (Button button : buttons) {
            button.update();
        }
        return true;
    }
}
