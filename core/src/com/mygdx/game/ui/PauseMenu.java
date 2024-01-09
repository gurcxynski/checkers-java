package com.mygdx.game.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class PauseMenu extends Menu {
    public PauseMenu() {
        super(true);

        addActor(new MyButton(
                (int) (WindowConfig.OUTSIDE_SQUARE * 0.9 - WindowConfig.MARGIN),
                (int) (WindowConfig.OUTSIDE_SQUARE * 0.9 - WindowConfig.MARGIN),
                (WindowConfig.OUTSIDE_SQUARE / 10), "settings_icon_transparent",
                new MyListener() {
                    public void onClick() {
                        Game.machine.resume();
                    }
                }));

        LinkedList<MyTextButton> buttons = new LinkedList<MyTextButton>();
        int n = Gdx.files.internal("assets/skins/").list().length;
        String[] names = { "gray", "wooden", "black and white" };
        for (int i = 1; i <= n; i++) {
            final int a = i;
            MyTextButton btn = new MyTextButton(
                    names[i - 1].toUpperCase(),
                    new MyListener() {
                        public void onClick() {
                            Game.style = a;
                            Game.machine.getBoard().updateColor();
                        }
                    });
            btn.disable = new ArrayList<>();
            if (i == Game.style)
                btn.setChecked(true);
            buttons.add(btn);
        }
        for (MyTextButton button : buttons) {
            for (MyTextButton other : buttons) {
                if (button != other) {
                    button.disable.add(other);
                }
            }
            super.addTextButton(button, 5f);
        }

        super.addTextButton(new MyTextButton("EXIT GAME", new MyListener() {
            public void onClick() {
                if (Game.machine.onlineGame) {
                    Game.machine.disconnectOnline();
                }
                Game.machine.toMenu(StartMenu.class);
            }
        }));

        addActor(super.table);
    }
}
