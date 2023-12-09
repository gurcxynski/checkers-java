package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class JoinMenu extends Menu {
    TextField field;

    public JoinMenu() {
        super();
        TextFieldStyle style = new TextFieldStyle();
        style.font = Game.skin.getFont("font");
        style.fontColor = Color.LIGHT_GRAY;
        style.background = Game.skin.getDrawable("buttonblank");

        field = new TextField("Click here to input IP", style);
        field.setAlignment(Align.center);

        field.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusListener.FocusEvent event, Actor actor, boolean focused) {
                if (focused) {
                    TextFieldStyle newStyle = new TextFieldStyle();
                    field.setText("");

                    newStyle.font = Game.skin.getFont("font");
                    newStyle.fontColor = Color.WHITE;
                    newStyle.background = Game.skin.getDrawable("buttonblankdown");
                    field.setStyle(newStyle);
                }
            }
        });

        super.table.add(field).pad(30).padBottom(70).width(WindowConfig.BUTTON_DEFAULT_WIDTH)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT / 2).row();

        super.addTextButton(new MyTextButton("CONNECT", new MyListener() {
            public void onClick() {
                Game.machine.joinOnlineGame(field.getText());
            }
        }));

        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OnlineMenu.class);
            }
        }));

        addActor(super.table);
    }
}
