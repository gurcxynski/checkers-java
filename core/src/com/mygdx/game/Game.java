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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.StateMachine.GameState;

public class Game extends ApplicationAdapter {

	public static Skin skin;

	public static StateMachine machine;
	public static int style = 0;

	@Override
	public void create() {
		skin = new Skin();

		loadTextures();
		loadFont();
		Styles.loadStyles();
		skin.add("default", Styles.buttonStyles[style]);
		machine = new StateMachine();
	}

	@Override
	public void render() {
		if (machine.state == GameState.AWATING_NETWORK)
			machine.checkForIncoming();
		machine.draw();
	}
	private void loadTextures() {
		loadTextures("assets/");
	}
	private void loadTextures(String path) {
		FileHandle[] files = Gdx.files.internal(path).list();
		
		for (FileHandle file : files) {
			if (file.isDirectory()) {
				this.loadTextures(path + file.name() + "/");
			} else {
				if (file.extension().equals("png")) {
					String textureName = file.nameWithoutExtension();
					skin.add(path.replace("assets/", "").replace("skins/", "").replace("/", "_") + textureName, new Texture(path + file.name()));
				}
			}
		}
	}

	private void loadFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = WindowConfig.FONT_SIZE_NORMAL;
		parameter.shadowOffsetY = 3;
		parameter.shadowOffsetX = 3;
		parameter.color = Color.WHITE;
		parameter.shadowColor = Color.BLACK;

		BitmapFont normalFont = generator.generateFont(parameter);
		parameter.size = WindowConfig.FONT_SIZE_HEADER;
		BitmapFont headerFont = generator.generateFont(parameter);

		generator.dispose();


		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = normalFont;

		skin.add("default", labelStyle);
		skin.add("font", normalFont, BitmapFont.class);
		skin.add("h_font", headerFont, BitmapFont.class);
	}

	public static void changeStyle(int i) {
		style = i;
		skin.remove("default", TextButtonStyle.class);
		skin.add("default", Styles.buttonStyles[style]);
		machine.updateStyles();
	}
}
