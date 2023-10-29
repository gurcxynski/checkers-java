package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Board extends Group {
    ArrayList<Piece> pieces;
    boolean playingWhite;

    public Board(boolean white) {
        playingWhite = white;
        initialize();
    }
    public Board() {
        playingWhite = true;
        initialize();
    }

    public Piece getField(int[] field) {
        for (Piece piece : pieces) {
            if (piece.GridX() == field[0] && piece.GridY() == field[1])
                return piece;
        }
        return null;
    }

    public Piece getField(int x, int y) {
        return getField(new int[] { x, y });
    }

    public boolean executeMove(Move move) {
        if (!Helpers.isValid(move)) return false;
        if (Helpers.mustCapture(move.ofWhite) && !Helpers.isCapture(move)) return false;
        // remove captured piece if its a capture
        if (Helpers.isCapture(move)) {
            int[] field = new int[] { (move.from[0] + move.to[0]) / 2, (move.from[1] + move.to[1]) / 2 };
            getField(field).hide();
        }
        getField(move.getFrom()).moveTo(move.to[0], move.to[1]);
        if ((move.ofWhite && move.to[1] == 7) || (!move.ofWhite && move.to[1] == 0)) {
            Globals.board.getField(move.to).kingMe();
        }
        return true;
    }

    public void initialize() {
        pieces = new ArrayList<Piece>();
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(i * 2, 0, true, false));
            pieces.add(new Piece(i * 2 + 1, 1, true, false));
            pieces.add(new Piece(i * 2, 2, true, false));

            pieces.add(new Piece(i * 2 + 1, 7, false, false));
            pieces.add(new Piece(i * 2, 6, false, false));
            pieces.add(new Piece(i * 2 + 1, 5, false, false));
        }
    }

    @Override
    public void draw(Batch batch, float p_alpha) {
        batch.draw(Globals.textures.get("board"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, 240, 240, false, playingWhite);
    }
    public boolean isDraw() {
        return false;
    }
}
