package com.mygdx.game;

public abstract class Helpers {
    public static boolean isValid(String move, boolean white) {
        if (move.length() != 4) return false;
        if (!inBoard(move.substring(0, 2)) || !inBoard(move.substring(2, 4))) return false;
        if (Globals.board.getField(move.substring(2,4)) != null) return false; // takes a piece
        if (Globals.board.getField(move.substring(0, 2)).isRed() == white) return false;
        return true;
    }
    public static boolean inBoard(String field) {
        int[] fieldCoords = convertField(field);
        return fieldCoords[0] >= 0 && fieldCoords[0] <= 7 && fieldCoords[1] >= 0 && fieldCoords[1] <= 7;
    }
    public int[] convertMove(String move) {
        int[] result = new int[4];
        result[0] = move.charAt(0) - 97;
        result[1] = move.charAt(1) - 49;
        result[2] = move.charAt(2) - 97;
        result[3] = move.charAt(3) - 49;
        return result;
    }
    public static int[] convertField(String field) {
        int[] result = new int[2];
        result[0] = field.charAt(0) - 97;
        result[1] = field.charAt(1) - 49;
        return result;
    }
}
