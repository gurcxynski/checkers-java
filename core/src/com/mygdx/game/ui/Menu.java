package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Game;
import com.mygdx.game.Styles;
import com.mygdx.game.WindowConfig;

public class Menu extends Stage {
    protected Table table;

    private boolean drawBackground = true;

    public void drawBackground() {
        Color color = Styles.getBackgroundColor(Game.style);
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void draw() {
        if (drawBackground)
            drawBackground();
        super.draw();
    }

    protected void addTextButton(TextButton button) {
        this.table.add(button).pad(WindowConfig.BUTTON_DEFAULT_PAD).width(WindowConfig.BUTTON_DEFAULT_WIDTH)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT).row();
    }

    protected void addTextButton(int span, TextButton button) {
        this.table.add(button).pad(WindowConfig.BUTTON_DEFAULT_PAD).width(WindowConfig.BUTTON_DEFAULT_WIDTH)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT).colspan(span).row();
    }

    protected void addTextButton(TextButton button, float pad) {
        this.table.add(button).pad(pad).width(WindowConfig.BUTTON_DEFAULT_WIDTH)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT).row();
    }

    protected void addTextButton(TextButton button, int width) {
        this.table.add(button).pad(WindowConfig.BUTTON_DEFAULT_PAD).width(width)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT).row();
    }

    protected void addTextButton(TextButton button, int pad, int width) {
        this.table.add(button).pad(pad).width(width).height(WindowConfig.BUTTON_DEFAULT_HEIGHT).row();
    }

    private void init() {
        this.table = new Table();
        this.table.setFillParent(true);
    }

    public Menu() {
        super();
        init();
    }

    public Menu(boolean drawBackground) {
        super();

        this.drawBackground = false;
        init();
    }
    public void updateStyles(){
        for (Actor button : table.getChildren()) {
            if (button instanceof MyTextButton) ((MyTextButton)button).updateStyle();
        }
    }
}
