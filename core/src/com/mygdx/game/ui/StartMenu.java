package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Game;

public class StartMenu extends Menu {
    public StartMenu() {
        super();

        Label label2 = new Label("CHECKERS", Game.skin, "h_font", Color.WHITE);
        label2.setAlignment(1);
        super.table.add(label2).padTop(0).padBottom(25).row();
        super.addTextButton(new MyTextButton("NEW GAME", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(NewGameMenu.class);
            }
        }));
        super.addTextButton(new MyTextButton("THEME SELECTION", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OptionsMenu.class);
            }
        }));

        super.addTextButton(new MyTextButton("QUIT", new MyListener() {
            public void onClick() {
                Gdx.app.exit();
            }
        }));

        addActor(super.table);
    }
}
