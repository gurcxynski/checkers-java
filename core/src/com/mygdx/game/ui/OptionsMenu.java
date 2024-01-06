package com.mygdx.game.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class OptionsMenu extends Menu {
    public OptionsMenu() {
        super();

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
                            if (Game.machine.ret_board != null)
                                Game.machine.ret_board.updateColor();
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

        super.table.add(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        })).pad(5).padTop(35).width(WindowConfig.BUTTON_DEFAULT_WIDTH).row();

        addActor(super.table);
    }
}
