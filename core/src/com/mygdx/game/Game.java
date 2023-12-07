package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.StateMachine.GameState;

public class Game extends ApplicationAdapter {

	public static Skin skin;
	public static StateMachine machine;
	public static int style = 1;
    public static final int marginX = 40;
    public static final int marginY = 40;

	@Override
	public void create() {
		skin = new Skin();
		loadTextures("assets/");

		skin.add("default", new TextButtonStyle(skin.getDrawable("buttonblank"), skin.getDrawable("buttonblankdown"), skin.getDrawable("buttonblank"), new BitmapFont()));

		machine = new StateMachine();
	}

	@Override
	public void render() {
		if (machine.state == GameState.AWATING_NETWORK)
			machine.checkForIncoming();
		machine.draw();
	}

	void loadTextures(String path) {
		FileHandle[] files = Gdx.files.internal(path).list();

		for (FileHandle file : files) {
			if (file.isDirectory()) {
				this.loadTextures(path + file.name() + "/");
			} else {
				String textureName = file.nameWithoutExtension();
				skin.add(textureName, new Texture(path + file.name()));
			}
		}
	}
}
