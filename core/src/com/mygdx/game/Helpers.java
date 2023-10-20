package com.mygdx.game;

public abstract class Helpers {
    public static boolean isValid(Move move) {
        if (move.from == null || move.to == null) return false;
        if (move.from.length != 2 || move.to.length != 2) return false;

        if (!inBoard(move.from) || !inBoard(move.to)) return false;
        if (Globals.board.getField(move.from) == null) return false; // from field is empty
        if (Globals.board.getField(move.from).isWhite() != move.ofWhite) return false; //not your piece
        if (Globals.board.getField(move.to) != null && Globals.board.getField(move.to).isWhite() == move.ofWhite) return false; // field is occupied by own piece
        if (Math.abs(move.from[0] - move.to[0]) != 1 || Math.abs(move.from[1] - move.to[1]) != 1) return false;

        return true;
    }
    public static boolean inBoard(int[] field) {
        return field[0] >= 0 && field[0] <= 7 && field[1] >= 0 && field[1] <= 7;
    }
    public static int[] convertMove(String move) {
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
    public static String convertCords(int x, int y) {
        return "" + (char)(x + 97) + (char)(y + 49);
    }
}
