package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends ApplicationAdapter {

	@Override
	public void create() {
		Globals.textures = new HashMap<String, Texture>();
		Globals.textures.put("black", new Texture("red.png"));
		Globals.textures.put("white", new Texture("white.png"));
		Globals.textures.put("bking", new Texture("redKing.png"));
		Globals.textures.put("wking", new Texture("whiteKing.png"));
		Globals.textures.put("menu", new Texture("menu.png"));
		Globals.textures.put("play_solo", new Texture("playsolo.png"));
		Globals.textures.put("play_multi", new Texture("playonline.png"));
		Globals.textures.put("quit", new Texture("quit.png"));
		Globals.textures.put("join", new Texture("join.png"));
		Globals.textures.put("host", new Texture("host.png"));
		Globals.textures.put("button_white", new Texture("wbutton.png"));
		Globals.textures.put("button_black", new Texture("bbutton.png"));
		Globals.textures.put("dark_tile", new Texture("dark_tile.png"));
		Globals.textures.put("light_tile", new Texture("light_tile.png"));

		Globals.machine = new StateMachine();
	}

	@Override
	public void render() {
		Globals.machine.update();
		Globals.machine.draw();
	}

	@Override
	public void dispose() {
		Globals.machine.dispose();
	}
}
