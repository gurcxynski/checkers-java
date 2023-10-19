package com.mygdx.game;

import java.util.ArrayList;

public class Board {
    ArrayList<Piece> pieces;
    public Piece getField(String field) {
        System.out.println(field);
        System.out.println(field.charAt(0) - 97);
        System.out.println(field.charAt(1) - 49);
        for (Piece piece : pieces) {
            if (piece.getX() == field.charAt(0) - 97 && piece.getY() == field.charAt(1) - 49) return piece;
        }
        return null;
    }
    public void ExecuteMove(Move move) {
        int[] to = Helpers.convertField(move.getTo());
        getField(move.getFrom()).moveTo(to[0], to[1]);
    } 
    public Board() {
        pieces = new ArrayList<Piece>();
		//for (int i = 0; i < 4; i++) {
		//	pieces.add(new Piece(false, true, i * 2 + 1, 0));
		//	pieces.add(new Piece(false, false, i * 2, 7));
		//	pieces.add(new Piece(false, true, i * 2, 1));
		//	pieces.add(new Piece(false, false, i * 2  + 1, 6));
		//	pieces.add(new Piece(false, true, i * 2 + 1, 2));
		//	pieces.add(new Piece(false, false, i * 2, 5));
		//}
        pieces.add(new Piece(false, false, 0, 2));
    }
}
