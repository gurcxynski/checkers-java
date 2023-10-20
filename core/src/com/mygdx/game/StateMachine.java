package com.mygdx.game;

import java.util.ArrayList;

public class StateMachine {
    boolean WhiteToMove = true;
    ArrayList<Move> moveList;
    public void ExecuteMove(Move move) {
        if (Globals.board.ExecuteMove(move)) {
            WhiteToMove = !WhiteToMove;
            moveList.add(move);
        }
    }
}
