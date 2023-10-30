package com.mygdx.game;

public class OnlineMenu extends Menu {    
    public OnlineMenu() {
        buttons.add(new Button(100, 50, Globals.textures.get("button_white")));
        buttons.add(new Button(100, 300, Globals.textures.get("button_black")));
        buttons.add(new Button(100, 550, Globals.textures.get("join")));
    }
}
