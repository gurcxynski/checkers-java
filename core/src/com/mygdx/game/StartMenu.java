package com.mygdx.game;

public class StartMenu extends Menu {
    
    public StartMenu() {
        buttons.add(new Button(100, 50, Globals.textures.get("play_solo")));
        buttons.add(new Button(100, 300, Globals.textures.get("play_multi")));
        buttons.add(new Button(100, 550, Globals.textures.get("quit")));
    }
}
