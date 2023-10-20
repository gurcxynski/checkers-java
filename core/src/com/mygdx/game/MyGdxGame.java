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

	//int done = 0;
	//String[] moves = {"a1b2", "b8c7", "b2a3", "c7b6", "a3b4", "b6c5"};
	String curW = "a1";
	String curB = "b8";
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
			int x = Gdx.input.getX() / 30;
			int y = 7 - Gdx.input.getY() / 30;
			System.out.println(x + " " + y);
			String to = Helpers.convertCords(x, y);
			if (machine.WhiteToMove) {
				if (machine.ExecuteMove(new Move(curW + to, machine.WhiteToMove))) curW = to;
			}
			else {
				if (machine.ExecuteMove(new Move(curB + to, machine.WhiteToMove))) curB = to;
			}
		}

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
