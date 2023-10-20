package com.mygdx.game;

import java.util.ArrayList;


public class Board {
    ArrayList<Piece> pieces;
    public Piece getField(int[] field) {
        for (Piece piece : pieces) {
            if (piece.getX() == field[0] && piece.getY() == field[1]) return piece;
        }
        return null;
    }
    public boolean ExecuteMove(Move move) {
        if (!Helpers.isValid(move)) return false;
        // remove captured piece if its a capture
        if (Helpers.isCapture(move)) {
            int[] field = new int[]{(move.from[0] + move.to[0]) / 2, (move.from[1] + move.to[1]) / 2};
            pieces.remove(getField(field));
        }
        getField(move.getFrom()).moveTo(move.to[0], move.to[1]);
        return true;
    } 
    public void Initialize() {
        pieces = new ArrayList<Piece>();
		// fill the starting board with pieces
        pieces.add(new Piece(0, 0, true, false));
        pieces.add(new Piece(1, 7, false, false));
    }
}
