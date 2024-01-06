package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Game;

public class WrongIPMenu extends Menu {
    public WrongIPMenu() {
        super();

        Label label = new Label("Wrong IP", Game.skin);
        super.table.add(label);
        super.table.row();
        super.addTextButton(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(JoinMenu.class);
            }
        }));
        addActor(super.table);
    }
}
