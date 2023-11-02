package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;

public class MyGdxGame extends ApplicationAdapter {

	@Override
	public void create() {
		Globals.textures = new HashMap<String, Texture>();

		loadTexture("black");
		loadTexture("white");
		loadTexture("blackking");
		loadTexture("whiteking");
		loadTexture("playsolo");
		loadTexture("playonline");
		loadTexture("quit");
		loadTexture("join");
		loadTexture("white_button");
		loadTexture("black_button");
		loadTexture("dark_tile");
		loadTexture("light_tile");
		loadTexture("youwon");
		loadTexture("youlost");
		loadTexture("whitewon");
		loadTexture("blackwon");

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

	void loadTexture(String name) {
		Globals.textures.put(name, new Texture(name + ".png"));
	}
}
