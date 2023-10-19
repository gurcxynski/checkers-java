package com.mygdx.game;

public class Move {
    String from;
    String to;
    boolean ofWhite;
    public Move(String from, String to, boolean white) {
        if (!Helpers.isValid(from + to, white)) return;
        this.from = from;
        this.to = to;
        ofWhite = white;
    }
    public Move(String text, boolean white) {
        if (!Helpers.isValid(text, white)) {
            return;
        }
        from = text.substring(0, 2);
        to = text.substring(2, 4);
        ofWhite = white;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
}
