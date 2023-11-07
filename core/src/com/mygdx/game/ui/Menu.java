package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Game;

public class Menu extends Stage {
    @Override
    public void draw() {
        getBatch().begin();
        getBatch().draw(Game.skin.get("light_tile", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getBatch().end();
        super.draw();
    }
}
