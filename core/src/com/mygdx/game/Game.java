package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.StateMachine.GameState;

public class Game extends ApplicationAdapter {

	public static Skin skin;
	public static StateMachine machine;

	@Override
	public void create() {
		skin = new Skin();
		loadTextures();
		machine = new StateMachine();
	}

	@Override
	public void render() {
		if (machine.state == GameState.AWATING_NETWORK)
			machine.checkForIncoming();
		machine.draw();
	}

	void loadTextures() {
		FileHandle[] files = Gdx.files.internal("./assets/").list();

		for (FileHandle file : files) {
			String textureName = file.nameWithoutExtension();
			skin.add(textureName, new Texture(file.name()));
		}

	}
}
