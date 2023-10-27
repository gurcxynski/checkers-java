package com.mygdx.game;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Menu {
    ArrayList<Button> buttons = new ArrayList<Button>();
    public Menu() {
        buttons.add(new Button(100, 50, Globals.textures.get("play")));
        buttons.add(new Button(100, 350, Globals.textures.get("quit")));
    }
    public void draw(Batch batch) {
        batch.begin();
        batch.draw(Globals.textures.get("menu"), 0, 0, 480, 480);
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

