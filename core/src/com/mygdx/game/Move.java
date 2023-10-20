package com.mygdx.game;

public class Move {
    int[] from;
    int[] to;
    boolean ofWhite;
    public Move(String text, boolean white) {
        int[] conv = Helpers.convertMove(text);
        System.out.println(conv[0] + " " + conv[1] + " " + conv[2] + " " + conv[3]);
        
        from = new int[]{conv[0], conv[1]};
        to = new int[]{conv[2], conv[3]};

        ofWhite = white;
    }
    public int[] getFrom(){
        return from;
    }
    public int[] getTo(){
        return to;
    }
}
