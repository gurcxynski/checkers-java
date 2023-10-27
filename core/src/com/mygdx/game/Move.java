package com.mygdx.game;

public class Move {
    int[] from;
    int[] to;
    boolean ofWhite;
    public Move(String text, boolean white) {
        int[] conv = Helpers.convertMove(text);
        
        from = new int[]{conv[0], conv[1]};
        to = new int[]{conv[2], conv[3]};

        ofWhite = white;
    }
    public Move(int[] from, int[] to, boolean white) {
        this.from = from;
        this.to = to;
        ofWhite = white;
    }
    public int[] getFrom(){
        return from;
    }
    public int[] getTo(){
        return to;
    }
    @Override 
    public String toString(){
        return "Move from " + from[0] + ", " + from[1] + " to " + to[0] + ", " + to[1] + " of " + ofWhite;
    }
}
