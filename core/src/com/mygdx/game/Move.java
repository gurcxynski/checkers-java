package com.mygdx.game;

import java.io.Serializable;

public class Move implements Serializable {
    int[] from;
    int[] to;
    boolean ofWhite;
    boolean hasKinged;
    boolean captured;

    public Move(String text, boolean white) {
        int[] conv = Helpers.convertMove(text);

        from = new int[] { conv[0], conv[1] };
        to = new int[] { conv[2], conv[3] };

        ofWhite = white;
        setCaptured();
    }

    public Move(int[] from, int[] to, boolean white) {
        this.from = from;
        this.to = to;
        ofWhite = white;
        setCaptured();
    }

    public int[] getFrom() {
        return from;
    }

    public int[] getTo() {
        return to;
    }

    public int lengthX() {
        return Math.abs(from[0] - to[0]);
    }

    public int lengthY() {
        return Math.abs(from[1] - to[1]);
    }

    /**
     * Sets the captured flag for the move if the conditions are met.
     * The captured flag indicates whether a piece was captured during the move.
     * The conditions for capturing a piece are:
     * - The move length in both X and Y directions is 2.
     * - The destination field is empty.
     * - There is a piece of the opposite color in the field jumped over.
     */
    private void setCaptured() {
        if (lengthX() != 2 || lengthY() != 2)
            return;

        // check if destination is empty
        if (((Board)Game.machine.activeStage).getPiece(to) != null)
            return;

        // get the jumped over field
        int[] field = new int[] { (from[0] + to[0]) / 2, (from[1] + to[1]) / 2 };
        Piece captured = ((Board)Game.machine.activeStage).getPiece(field);

        // check if captured piece exists and is of opposite color
        if (captured == null)
            return;
        if (captured.isWhite() == ofWhite)
            return;

        this.captured = true;
    }

    @Override
    public String toString() {
        return "Move from " + from[0] + ", " + from[1] + " to " + to[0] + ", " + to[1] + " of " + ofWhite;
    }

    public boolean isCapture() {
        return captured;
    }
}
