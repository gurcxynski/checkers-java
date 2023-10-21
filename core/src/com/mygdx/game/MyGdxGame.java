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
	Piece held;
	int cellX;
	int cellY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		red = new Texture("red.png");
		white = new Texture("white.png");
		boardTexture = new Texture("board.png");
		machine = new StateMachine();
		machine.NewGame();
	}

	@Override
	public void render () {

		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX() / 60;
			int y = 7 - Gdx.input.getY() / 60;
			String to = Helpers.convertCords(x, y);
			if (Globals.board.getField(x, y) != null) { 
				held = Globals.board.getField(x, y);
				System.out.println("Held " + held.getX() + " " + held.getY());
			}
			else if (held != null) machine.ExecuteMove(new Move(Helpers.convertCords(held.getX(), held.getY()) + to, machine.WhiteToMove));
		}

		batch.begin();

		ScreenUtils.clear(Color.SKY);
		batch.draw(boardTexture, 0, 0, 0, 0, 480, 480, 1, 1, 0, 0, 0, 240, 240, false, !whitePlayer);
		for (Piece piece : Globals.board.pieces) {
			batch.draw(piece.isWhite() ? white : red, piece.getX() * 60, whitePlayer ? piece.getY() * 60 : 420 - piece.getY() * 60, 60, 60);
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
