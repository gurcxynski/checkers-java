package com.mygdx.game;


public abstract class Helpers {
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
