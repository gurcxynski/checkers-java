package com.mygdx.game;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        buttons.add(new Button(100, 20, Globals.textures.get("button_white")));
        buttons.add(new Button(100, 140, Globals.textures.get("button_red")));
        buttons.add(new Button(100, 260, Globals.textures.get("join")));
    }
}
