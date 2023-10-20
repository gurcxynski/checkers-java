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
        if (Globals.board.getField(move.getTo()) != null) getField(move.getTo()).remove();
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
