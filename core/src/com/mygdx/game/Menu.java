package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Menu extends Group {
    @Override
    public void draw(Batch batch, float p_alpha) {
        batch.draw(Globals.textures.get("menu"), 0, 0, 800, 800);
    }
    public boolean update() {
        return false;
    }
}
