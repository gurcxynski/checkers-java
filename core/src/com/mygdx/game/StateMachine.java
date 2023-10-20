package com.mygdx.game;

import java.util.ArrayList;

public class StateMachine {
    public enum GameState {
        MOVING,
        AWATING_ENEMY_MOVE,
        WHITE_WON,
        BLACK_WON,
        DRAW,

    }
    boolean WhiteToMove = true;
    ArrayList<Move> moveList;
    public void NewGame() {
        Globals.board = new Board();
        Globals.board.Initialize();
        moveList = new ArrayList<Move>();
    }
    public boolean ExecuteMove(Move move) {
        if (Globals.board.ExecuteMove(move)) {
            WhiteToMove = !WhiteToMove;
            moveList.add(move);
            return true;
        }
        return false;
    }
}
