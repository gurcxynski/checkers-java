package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {
	Piece held;
	public boolean whitePlayer = true;
    Scene(boolean white) {
        whitePlayer = white;
    }
    Move Update() {
        if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX() / 60;
			int y = 7 - Gdx.input.getY() / 60;
			String to = Helpers.convertCords(x, y);
			if (Globals.board.getField(x, y) != null) { 
				held = Globals.board.getField(x, y);
				System.out.println("Held " + held.getX() + " " + held.getY());
			}
			else if (held != null) return new Move(Helpers.convertCords(held.getX(), held.getY()) + to, whitePlayer);
		}
        return null;
    }
    void Draw(SpriteBatch batch, HashMap<String, Texture> textures) {
        batch.draw(textures.get("board"), 0, 0, 0, 0, 480, 480, 1, 1, 0, 0, 0, 240, 240, false, !whitePlayer);
		for (Piece piece : Globals.board.pieces) {
			batch.draw(piece.isWhite() ? 
            (piece.isKing() ? textures.get("wking") : textures.get("white")) : 
            (piece.isKing() ? textures.get("rking") : textures.get("red")), 
            piece.getX() * 60, whitePlayer ? piece.getY() * 60 : 420 - piece.getY() * 60, 60, 60);
		}
    }
}
