package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class Menu extends Stage {
    protected Table table;

    @Override
    public void draw() {
        getBatch().begin();
        getBatch().draw(Game.skin.get("light_tile", Texture.class), 0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        getBatch().end();
        super.draw();
    }

    protected void addTextButton(TextButton button) {
        this.table.add(button).pad(WindowConfig.BUTTON_DEFAULT_PAD).width(WindowConfig.BUTTON_DEFAULT_WIDTH).row();
    }

    protected void addTextButton(TextButton button, float pad) {
        this.table.add(button).pad(pad).width(WindowConfig.BUTTON_DEFAULT_WIDTH).row();
    }

    protected void addTextButton(TextButton button, int width) {
        this.table.add(button).pad(WindowConfig.BUTTON_DEFAULT_PAD).width(width).row();
    }

    protected void addTextButton(TextButton button, int pad, int width) {
        this.table.add(button).pad(pad).width(width).row();
    }

    public Menu() {
        super();

        this.table = new Table();
        this.table.setFillParent(true);
    }

}
