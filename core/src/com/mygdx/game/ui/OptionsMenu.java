package com.mygdx.game.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import com.mygdx.game.Game;
import com.mygdx.game.Styles;
import com.mygdx.game.WindowConfig;

public class OptionsMenu extends Menu {
    public OptionsMenu() {
        super();

        LinkedList<MyTextButton> buttons = new LinkedList<MyTextButton>();
        int n = Styles.buttonStyles.length;
        for (int i = 0; i < n; i++) {
            int a = i;
            MyTextButton btn = new MyTextButton(
                    Styles.styles[i].toUpperCase(),
                    new MyListener() {
                        public void onClick() {
                            Game.changeStyle(a);
                            if (Game.machine.getBoard() != null)
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

        super.table.add(new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(StartMenu.class);
            }
        })).pad(10).padTop(35).width(WindowConfig.BUTTON_DEFAULT_WIDTH)
        .height((int)(WindowConfig.BUTTON_DEFAULT_HEIGHT * 0.75)).row();

        addActor(super.table);
    }
}
