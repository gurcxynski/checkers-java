package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Board extends Actor {
    ArrayList<Piece> pieces;
    Piece held;

    public Board() {
        initialize();
    }

    public Piece getField(int[] field) {
        for (Piece piece : pieces) {
            if (piece.GridX() == field[0] && piece.GridY() == field[1])
                return piece;
        }
        return null;
    }

    public Piece getField(int x, int y) {
        return getField(new int[] { x, y });
    }

    public boolean executeMove(Move move) {
        //if (!Helpers.isValid(move)) return false;
        //if (Helpers.mustCapture(move.ofWhite) && !Helpers.isCapture(move)) return false;
        //if (Globals.machine.moveList.size() > 1)
        //{
        //    Move lastMove = Globals.machine.moveList.get(Globals.machine.moveList.size() - 1);
        //    if (lastMove.ofWhite == move.ofWhite && move.from != lastMove.to) { System.out.println("you have to capture with" + 
        //    Helpers.convertCords(lastMove.to[0], lastMove.to[1])); return false; }
        //} 
        //// remove captured piece if its a capture
        //if (Helpers.isCapture(move)) {
        //    int[] field = new int[] { (move.from[0] + move.to[0]) / 2, (move.from[1] + move.to[1]) / 2 };
        //    Piece captured = getField(field);
        //    captured.hide();
        //    pieces.remove(captured);
        //    move.captured = true;
        //}
        //getField(move.getFrom()).moveTo(move.to[0], move.to[1]);
        //if ((move.ofWhite && move.to[1] == 7) || (!move.ofWhite && move.to[1] == 0)) {
        //    Globals.board.getField(move.to).kingMe();
        //}

        getField(move.from).moveTo(move.to);
        capture(move.captured); // does nothing if not a capture (captured is null)
        return true;
    }



    public void initialize() {
        pieces = new ArrayList<Piece>();
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(i * 2, 0, true));
            pieces.add(new Piece(i * 2 + 1, 1, true));
            pieces.add(new Piece(i * 2, 2, true));
        
            pieces.add(new Piece(i * 2 + 1, 7, false));
            pieces.add(new Piece(i * 2, 6, false));
            pieces.add(new Piece(i * 2 + 1, 5, false));
        }
    }

    @Override
    public void draw(Batch batch, float p_alpha) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                    String text = ((i + j) % 2 != 0 ? "light" : "dark") + "_tile";
                    batch.draw(Globals.textures.get(text), i * 100, j * 100, 100, 100);
            }
        }
    }
    public boolean isDraw() {
        return false;
    }
    public boolean isGameOver() {
        boolean color = pieces.get(0).isWhite();
        for (Piece piece : pieces) {
            if (piece.isWhite() != color) return false;
        }
        return true;
    }
    public boolean getWinner() {
        return pieces.get(0).isWhite();
    }
    public boolean handleClick(int x, int y) {
        // return true if executed move
        String clickedField = Helpers.convertCords(x, y);
        Piece clickedPiece = Globals.board.getField(x, y);

        if (clickedPiece != null && clickedPiece.isWhite() == Globals.machine.isTurnOf()) 
        {
            held = Globals.board.getField(x, y);
            System.out.println("holding " + clickedField);
            return false;
        } 
        if (held != null && clickedPiece == null)
        {
            Move move = new Move(held.getFieldString() + clickedField, Globals.machine.isTurnOf());
            System.out.println("trying to move " + held.getFieldString() + " to " + clickedField);
            if (!isValid(move)) return false;
            System.out.println("valid move");
            executeMove(move);
            held = null;
            return true;
        }
        return false;
    }

    private boolean isValid(Move move) {
        // move object internally invalid
        if (move.from == null || move.to == null) return false;
        if (move.from.length != 2 || move.to.length != 2) return false;
        System.out.println("proper move object");

        // move to a field outside the board
        if (!Helpers.inBoard(move.from) || !Helpers.inBoard(move.to)) return false;
        System.out.println("in board");

        // move length not proper
        if (!isProperLength(move)) return false;
        System.out.println("proper length");

        // move backwards as not-king
        if (!isProperDirection(move)) return false;
        System.out.println("proper direction");

        // move to an occupied field
        if (getField(move.to) != null) return false;
        System.out.println("destination is empty");

        return true;
    }

    private boolean isProperLength(Move move) {
        int c_len = move.isCapture() ? 2 : 1;
        return (move.lengthX() == c_len && move.lengthY() == c_len);
    }
    private boolean isProperDirection(Move move) {
        return (move.ofWhite && move.from[1] < move.to[1]) || (!move.ofWhite && move.from[1] > move.to[1]) || getField(move.from).isKing();
    }
    private void capture(Piece captured) {
        if (captured == null) return;
        captured.hide();
        pieces.remove(captured);
    }
}