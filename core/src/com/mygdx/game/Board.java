package com.mygdx.game;

import java.util.ArrayList;

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
        if (Helpers.mustCaptureWith(move.from) && !Helpers.isCapture(move)) return false;
        // remove captured piece if its a capture
        if (Helpers.isCapture(move)) {
            int[] field = new int[] { (move.from[0] + move.to[0]) / 2, (move.from[1] + move.to[1]) / 2 };
            Piece captured = getField(field);
            captured.hide();
            pieces.remove(captured);
            move.captured = true;
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
            pieces.add(new Piece(i * 2, 0, true));
            pieces.add(new Piece(i * 2 + 1, 1, true));
            pieces.add(new Piece(i * 2, 2, true));
        
            pieces.add(new Piece(i * 2 + 1, 7, false));
            pieces.add(new Piece(i * 2, 6, false));
            pieces.add(new Piece(i * 2 + 1, 5, false));
        }
    }

    @Override
    public void draw(Batch batch, float p_alpha) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                    String text = ((i + j) % 2 != 0 ? "light" : "dark") + "_tile";
                    batch.draw(Globals.textures.get(text), i * 100, j * 100, 100, 100);
            }
        }
    }
    public boolean isDraw() {
        return false;
    }
    public boolean isGameOver() {
        boolean color = pieces.get(0).isWhite();
        for (Piece piece : pieces) {
            if (piece.isWhite() != color) return false;
        }
        return true;
    }
    public boolean getWinner() {
        return pieces.get(0).isWhite();
    }
}
