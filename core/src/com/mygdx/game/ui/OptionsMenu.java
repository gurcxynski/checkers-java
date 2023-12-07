package com.mygdx.game.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;

public class OptionsMenu extends Menu {
    public OptionsMenu() {
        LinkedList<MyTextButton> buttons = new LinkedList<MyTextButton>();
        int n = Gdx.files.internal("assets/skins/").list().length;
        String[] names = { "gray", "wooden", "black and white" };
        for (int i = 1; i <= n; i++) {
            final int a = i;
            MyTextButton btn = new MyTextButton(
                i, names[i - 1].toUpperCase(),
                    new MyListener() {
                        public void onClick() {
                            Game.style = a;
                        }
                    });
            btn.disable = new ArrayList<>();
            if (i == 1) btn.setChecked(true);
            buttons.add(btn);
        }
        for (MyTextButton button : buttons) {
            for (MyTextButton other : buttons) {
                if (button != other) {
                    button.disable.add(other);
                }
            }
            addActor(button);
        }
        addActor(new MyTextButton(0, "BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        }));
    }
}
