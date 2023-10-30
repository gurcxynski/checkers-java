package com.mygdx.game;


public abstract class Helpers {
    public static boolean isValid(Move move) {
        if (move.from == null || move.to == null) return false;
        if (move.from.length != 2 || move.to.length != 2) return false;

        if (!inBoard(move.from) || !inBoard(move.to)) return false;
        Piece from = Globals.board.getField(move.from);
        Piece to = Globals.board.getField(move.to);

        if (from == null) return false; // from field is empty

        if (from.isWhite() != move.ofWhite) return false; // not your piece
        if (to != null) return false; // field is occupied
        if ((Math.abs(move.from[0] - move.to[0]) != 1 || Math.abs(move.from[1] - move.to[1]) != 1) && !isCapture(move)) return false;
        // disallow moves backwards
        if (((from.isWhite() && move.from[1] > move.to[1]) || (!from.isWhite() && move.from[1] < move.to[1])) && !from.isKing()) return false;
        return true;
    }
    public static boolean isCapture (Move move) {
        if (!(Math.abs(move.from[0] - move.to[0]) == 2 && Math.abs(move.from[1] - move.to[1]) == 2)) return false;
        int[] field = new int[]{(move.from[0] + move.to[0]) / 2, (move.from[1] + move.to[1]) / 2};
        if (Globals.board.getField(field) == null) return false;
        if (Globals.board.getField(field).isWhite() == move.ofWhite) return false;
        return true;
    }
    public static boolean mustCapture(boolean white) {
        for (Piece piece : Globals.board.pieces) {
            if (piece.isWhite() == white && mustCaptureWith(new int[] {piece.GridX(), piece.GridY()})) return true;
        }
        return false;
    }
    public static boolean mustCaptureWith(int[] field) {
        Piece piece = Globals.board.getField(field);
        Move[] moves = { 
            new Move(new int[]{piece.GridX(), piece.GridY()}, new int[]{piece.GridX() + 2, piece.GridY() + 2}, piece.isWhite()),
            new Move(new int[]{piece.GridX(), piece.GridY()}, new int[]{piece.GridX() + 2, piece.GridY() - 2}, piece.isWhite()),
            new Move(new int[]{piece.GridX(), piece.GridY()}, new int[]{piece.GridX() - 2, piece.GridY() - 2}, piece.isWhite()),
            new Move(new int[]{piece.GridX(), piece.GridY()}, new int[]{piece.GridX() - 2, piece.GridY() + 2}, piece.isWhite())
        };
        for (Move move : moves) if (isValid(move) && isCapture(move)) return true;
        return false;
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
    public static boolean drawRedDown() {
        if (!Globals.machine.onlineGame) return false;
        if (Globals.machine.playingWhiteOnline) return false;
        return true;
    }
}
