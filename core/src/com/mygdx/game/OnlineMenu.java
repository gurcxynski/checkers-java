package com.mygdx.game;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        buttons.add(new Button(100, 100, Globals.textures.get("host")));
        buttons.add(new Button(100, 220, Globals.textures.get("join")));
    }
}
