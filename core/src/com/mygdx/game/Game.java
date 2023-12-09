package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.StateMachine.GameState;

public class Game extends ApplicationAdapter {

	public static Skin skin;

	public static StateMachine machine;
	public static int style = 1;

	@Override
	public void create() {
		skin = new Skin();

		loadTextures("assets/");
		loadFont();

		machine = new StateMachine();
	}

	@Override
	public void render() {
		if (machine.state == GameState.AWATING_NETWORK)
			machine.checkForIncoming();
		machine.draw();
	}

	private void loadTextures(String path) {
		FileHandle[] files = Gdx.files.internal(path).list();

		for (FileHandle file : files) {
			if (file.isDirectory()) {
				this.loadTextures(path + file.name() + "/");
			} else {
				if (file.extension().equals("png")) {
					String textureName = file.nameWithoutExtension();
					skin.add(textureName, new Texture(path + file.name()));
				}
			}
		}
	}

	private void loadFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;
		parameter.shadowOffsetY = 3;
		parameter.shadowOffsetX = 3;
		parameter.color = Color.WHITE;
		parameter.shadowColor = Color.BLACK;

		BitmapFont customFont = generator.generateFont(parameter);
		generator.dispose();

		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.up = skin.getDrawable("buttonblank");
		buttonStyle.down = skin.getDrawable("buttonblankdown");
		buttonStyle.checked = skin.getDrawable("buttonchecked");
		buttonStyle.font = customFont;

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = customFont;

		skin.add("default", buttonStyle);
		skin.add("default", labelStyle);
		skin.add("font", customFont, BitmapFont.class);
	}
}
