package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.mygdx.game.Game;

public class JoinMenu extends Menu {
    TextField field;
    public JoinMenu() {
        TextFieldStyle style = new TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;
        style.background = Game.skin.getDrawable("dark_tile");
        field = new TextField("127.0.0.1", style);
        field.setBounds(200, 600, 100, 50);
        addActor(field);
        addActor(new MyTextButton(1, "JOIN", new MyListener() {
	        public void onClick() { Game.machine.joinOnlineGame(field.getText()); }
        }));
        addActor(new MyTextButton(0, "BACK", new MyListener() {
	        public void onClick() { Game.machine.toMenu(OnlineMenu.class); }
        }));
    }
}
