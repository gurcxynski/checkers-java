package com.mygdx.game;

public class StartMenu extends Menu {
    
    public StartMenu() {
        buttons.add(new Button(100, 50, Globals.textures.get("playsolo")));
        buttons.add(new Button(100, 300, Globals.textures.get("playonline")));
        buttons.add(new Button(100, 550, Globals.textures.get("quit")));
    }
}
