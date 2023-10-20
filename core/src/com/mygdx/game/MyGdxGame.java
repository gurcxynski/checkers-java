package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture red;
	Texture white;
	Texture boardTexture;
	StateMachine machine;
	final boolean whitePlayer = true;

	int done = 0;
	String[] moves = {"a1a2", "b8b7", "a2a3", "b7b6", "a3a4", "b6b5"};
	@Override
	public void create () {
		batch = new SpriteBatch();
		red = new Texture("red.png");
		white = new Texture("white.png");
		boardTexture = new Texture("board.png");
		Globals.board = new Board();
		machine = new StateMachine();
	}

	@Override
	public void render () {
		if (Gdx.input.justTouched()) machine.ExecuteMove(new Move(moves[done++], machine.WhiteToMove));

		batch.begin();

		ScreenUtils.clear(Color.SKY);
		batch.draw(boardTexture, 0, 0, 0, 0, 240, 240, 1, 1, 0, 0, 0, 240, 240, false, !whitePlayer);
		for (Piece piece : Globals.board.pieces) {
			batch.draw(piece.isWhite() ? white : red, piece.getX() * 30, whitePlayer ? piece.getY() * 30 : 210 - piece.getY() * 30, 30, 30);
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
