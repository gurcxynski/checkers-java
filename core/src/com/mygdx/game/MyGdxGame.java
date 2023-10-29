package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends ApplicationAdapter {

	@Override
	public void create () {
		Globals.textures = new HashMap<String, Texture>();
		Globals.textures.put("red", new Texture("red.png"));
		Globals.textures.put("white", new Texture("white.png"));
		Globals.textures.put("rking", new Texture("redKing.png"));
		Globals.textures.put("wking", new Texture("whiteKing.png"));
		Globals.textures.put("board", new Texture("board.png"));
		Globals.textures.put("menu", new Texture("menu.png"));
		Globals.textures.put("play_solo", new Texture("playsolo.png"));
		Globals.textures.put("play_multi", new Texture("playonline.png"));
		Globals.textures.put("quit", new Texture("quit.png"));

		Globals.machine = new StateMachine();
	}

	@Override
	public void render () {
		Globals.machine.update();
		Globals.machine.draw();
	}
	@Override
	public void dispose () {
		Globals.machine.dispose();
	}
}
