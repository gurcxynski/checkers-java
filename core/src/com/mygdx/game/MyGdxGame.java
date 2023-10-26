package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	StateMachine machine;
	HashMap<String, Texture> textures;
	@Override
	public void create () {
		batch = new SpriteBatch();

		textures = new HashMap<String, Texture>();
		textures.put("red", new Texture("red.png"));
		textures.put("white", new Texture("white.png"));
		textures.put("rking", new Texture("redKing.png"));
		textures.put("wking", new Texture("whiteKing.png"));
		textures.put("board", new Texture("board.png"));

		machine = new StateMachine();
		machine.NewGame(true);
	}

	@Override
	public void render () {

		batch.begin();

		machine.Update();
		machine.Draw(batch, textures);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
