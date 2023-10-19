package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture red;
	Texture white;
	Texture boardTexture;

	@Override
	public void create () {
		batch = new SpriteBatch();
		red = new Texture("red.png");
		white = new Texture("white.png");
		boardTexture = new Texture("board.png");
		Globals.board = new Board();
	}

	@Override
	public void render () {

		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			Globals.board.ExecuteMove(new Move("a3b4", true));
		}

		batch.begin();

		ScreenUtils.clear(Color.SKY);
		batch.draw(boardTexture, 0, 0, 240, 240);
		for (Piece piece : Globals.board.pieces) {
			batch.draw(piece.isRed() ? red : white, piece.getX() * 30, piece.getY() * 30, 30, 30);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		red.dispose();
		white.dispose();
	}
}
