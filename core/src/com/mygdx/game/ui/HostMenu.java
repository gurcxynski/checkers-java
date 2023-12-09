package com.mygdx.game.ui;

import java.util.ArrayList;

import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class HostMenu extends Menu {
    public HostMenu() {
        final MyButton white = new MyButton((int) (WindowConfig.INSIDE_SQUARE * 0.1),
                (int) (WindowConfig.INSIDE_SQUARE * 0.75),
                (int) (WindowConfig.INSIDE_SQUARE * 0.3), (int) (WindowConfig.INSIDE_SQUARE * 0.3),
                "white_unchecked", "white_checked", "white_checked", new MyListener());
        final MyButton black = new MyButton((int) (WindowConfig.INSIDE_SQUARE * 0.6),
                (int) (WindowConfig.INSIDE_SQUARE * 0.75),
                (int) (WindowConfig.INSIDE_SQUARE * 0.3), (int) (WindowConfig.INSIDE_SQUARE * 0.3),
                "black_unchecked", "black_checked", "black_checked", new MyListener());

        white.setChecked(true);

        white.disable = new ArrayList<>();
        black.disable = new ArrayList<>();
        white.disable.add(black);
        black.disable.add(white);

        addActor(white);
        addActor(black);

        super.addTextButton(new MyTextButton("HOST", new MyListener() {
            public void onClick() {
                Game.machine.hostOnlineGame(white.isChecked());
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
