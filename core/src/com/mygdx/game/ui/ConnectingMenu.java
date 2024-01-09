package com.mygdx.game.ui;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Game;

public class ConnectingMenu extends Menu {
    public ConnectingMenu() {
        super();

        Label label;
        try {
            label = new Label("Hosting on " + InetAddress.getLocalHost().getHostAddress() + "...", Game.skin);
            super.table.add(label).padBottom(20);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        super.table.row();
        super.addTextButton(new MyTextButton("CANCEL", new MyListener() {
            public void onClick() {
                Game.machine.cancelHosting();
                Game.machine.toMenu(HostMenu.class);
            }
        }));
        addActor(super.table);
    }
}
