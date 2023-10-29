package com.mygdx.game;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Menu {
    ArrayList<Button> buttons = new ArrayList<Button>();
    public Menu() {
        buttons.add(new Button(100, 100, Globals.textures.get("play_solo")));
        buttons.add(new Button(100, 220, Globals.textures.get("play_multi")));
        buttons.add(new Button(100, 340, Globals.textures.get("quit")));
    }
    public void draw(Batch batch) {
        batch.begin();
        batch.draw(Globals.textures.get("menu"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(Button button : buttons) {
            button.draw(batch);
        }
        batch.end();
    }

    public boolean update() {
        for(Button button : buttons) {
            button.update();
        }
        return true;
    }
}

