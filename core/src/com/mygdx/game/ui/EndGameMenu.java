package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Game;

public class EndGameMenu extends Menu {
    public EndGameMenu(boolean winner, boolean hasDisconnected) {
        super(false);
        Label label = new Label(
                hasDisconnected ? "OPPONENT DISCONNECTED" : (winner ? "WHITE" : "BLACK") + " WON!", Game.skin);
        super.table.add(label);
        super.table.row();
        super.table.add(new MyTextButton("MAIN MENU", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        }));
        addActor(super.table);
    }
}
