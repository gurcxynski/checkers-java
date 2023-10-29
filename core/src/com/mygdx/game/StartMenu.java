package com.mygdx.game;

public class StartMenu extends Menu {
    
    public StartMenu() {
        buttons.add(new Button(100, 100, Globals.textures.get("play_solo")));
        buttons.add(new Button(100, 220, Globals.textures.get("play_multi")));
        buttons.add(new Button(100, 340, Globals.textures.get("quit")));
    }
}
