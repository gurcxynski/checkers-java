package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Game;

public class EndGameMenu extends Stage {
    public EndGameMenu(boolean winner) {
        addActor(new MyTextButton(250, 350, (winner ? "WHITE" : "BLACK") + " WON", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        }));
    }
}
