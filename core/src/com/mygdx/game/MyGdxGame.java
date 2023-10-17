package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture red;
	Texture white;
	Texture board;
	ArrayList<Piece> pieces = new ArrayList<>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		red = new Texture("red.png");
		white = new Texture("white.png");
		board = new Texture("board.png");

		for (int i = 0; i < 4; i++) {
			pieces.add(new Piece(false, true, i * 2 + 1, 0));
			pieces.add(new Piece(false, false, i * 2, 7));
			pieces.add(new Piece(false, true, i * 2, 1));
			pieces.add(new Piece(false, false, i * 2  + 1, 6));
			pieces.add(new Piece(false, true, i * 2 + 1, 2));
			pieces.add(new Piece(false, false, i * 2, 5));
		}
	}

	@Override
	public void render () {
		batch.begin();

		ScreenUtils.clear(Color.SKY);
		batch.draw(board, 0, 0, 240, 240);
		for (Piece piece : pieces) {
			batch.draw(piece.isRed() ? red : white, piece.getX() * 30, piece.getY() * 30, 30, 30);
		}
		batch.end();

		//if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
		//	piece.move(piece.getX() - 1, piece.getY());
   		//}
		//if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
		//	piece.move(piece.getX() + 1, piece.getY());
   		//}
		//if (Gdx.input.isKeyJustPressed(Keys.UP)) {
		//	piece.move(piece.getX(), piece.getY() + 1);
   		//}
		//if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
		//	piece.move(piece.getX(), piece.getY() - 1);
   		//}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		red.dispose();
		white.dispose();
	}
}
