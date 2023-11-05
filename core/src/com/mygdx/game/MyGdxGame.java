package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame extends ApplicationAdapter {

    public static Skin skin;
    public static StateMachine machine;

	@Override
	public void create() {

		skin = new Skin();
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

		machine = new StateMachine();
	}

	@Override
	public void render() {
		machine.update();
		machine.draw();
	}

	void loadTexture(String name) {
		skin.add(name, new Texture(name + ".png"));
	}
}
