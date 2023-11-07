package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
        addActor(new MyButton(100, 350, "join", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    Game.machine.joinOnlineGame(field.getText());
                }
            }
        }));
    }
}
