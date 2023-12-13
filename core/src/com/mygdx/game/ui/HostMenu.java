package com.mygdx.game.ui;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class HostMenu extends Menu {
    public HostMenu() throws UnknownHostException {
        super();

        final MyButton white = new MyButton(WindowConfig.BUTTON_DEFAULT_HEIGHT,
                "white_unchecked", "white_checked", "white_checked", new MyListener());
        final MyButton black = new MyButton(WindowConfig.BUTTON_DEFAULT_HEIGHT,
                "black_unchecked", "black_checked", "black_checked", new MyListener());

        white.setChecked(true);

        white.disable = new ArrayList<>();
        black.disable = new ArrayList<>();
        white.disable.add(black);
        black.disable.add(white);

        Label label = new Label("Choose \na color!", Game.skin);

        super.table.add(white).width(WindowConfig.BUTTON_DEFAULT_HEIGHT)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT).pad(20);
        super.table.add(label);
        super.table.add(black).width(WindowConfig.BUTTON_DEFAULT_HEIGHT)
                .height(WindowConfig.BUTTON_DEFAULT_HEIGHT).pad(20);
        super.table.row();

        super.addTextButton(3, new MyTextButton("HOST", new MyListener() {
            public void onClick() {
                Game.machine.hostOnlineGame(white.isChecked());
            }
        }));

        super.addTextButton(3, new MyTextButton("Game will be hosted at:\n" + InetAddress.getLocalHost().getHostAddress(), new MyListener()));

        super.addTextButton(3, new MyTextButton("BACK", new MyListener() {
            public void onClick() {
                Game.machine.toMenu(OnlineMenu.class);
            }
        }));

        addActor(super.table);
    }
}
