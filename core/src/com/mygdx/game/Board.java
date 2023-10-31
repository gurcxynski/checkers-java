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

    public Piece getPiece(int[] field) {
        for (Piece piece : pieces) {
            if (piece.GridX() == field[0] && piece.GridY() == field[1])
                return piece;
        }
        return null;
    }

    public Piece getPiece(int x, int y) {
        return getPiece(new int[] { x, y });
    }

    public boolean executeMove(Move move) {
        // move the piece

        getPiece(move.from).moveTo(move.to);

        capture(move.captured); // does nothing if not a capture (captured is null)

        // king the piece if it reached the end and is not king
        if (!getPiece(move.to).isKing() && (move.ofWhite && move.to[1] == 7) || (!move.ofWhite && move.to[1] == 0)) {
            getPiece(move.to).kingMe();
            move.hasKinged = true;
        }

        Globals.machine.moveList.add(move);
        
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
            if (piece.isWhite() != color)
                return false;
        }
        return true;
    }

    public boolean getWinner() {
        return pieces.get(0).isWhite();
    }

    boolean hasToCapture(boolean white) {
        for (Piece piece : pieces) {
            if (white == piece.isWhite() && hasToCapture(piece))
                return true;
        }
        return false;
    }

    boolean hasToCapture(Piece piece) {
        Move[] moves = {
                new Move(piece.getField(), new int[] { piece.GridX() + 2, piece.GridY() + 2 }, piece.isWhite()),
                new Move(piece.getField(), new int[] { piece.GridX() + 2, piece.GridY() - 2 }, piece.isWhite()),
                new Move(piece.getField(), new int[] { piece.GridX() - 2, piece.GridY() - 2 }, piece.isWhite()),
                new Move(piece.getField(), new int[] { piece.GridX() - 2, piece.GridY() + 2 }, piece.isWhite())
        };
        for (Move move : moves) {
            if (isValid(move) && move.isCapture())
                return true;
        }
        return false;
    }

    public boolean handleClick(int x, int y) {
        // return true if executed move
        String clickedField = Helpers.convertCords(x, y);
        Piece clickedPiece = Globals.board.getPiece(x, y);

        // pick up a piece
        if (clickedPiece != null && clickedPiece.isWhite() == Globals.machine.isTurnOf()) {
            held = Globals.board.getPiece(x, y);
            System.out.println("holding " + clickedField);
            return false;
        }
        // execute a move
        if (held != null && clickedPiece == null) {
            Move move = new Move(held.getFieldString() + clickedField, Globals.machine.isTurnOf());
            System.out.println("trying to move " + held.getFieldString() + " to " + clickedField);

            if (!isValid(move))
                return false;
            System.out.println("valid move");

            // force captures
            Move last = Globals.machine.lastMove();
            if (last != null && !last.hasKinged && last.ofWhite == Globals.machine.isTurnOf()
                    && !(sameField(move.from, last.to) && move.isCapture())) {
                System.out.println("move is from " + move.from[0] + " " + move.from[1] + " last move was to "
                        + last.to[0] + " " + last.to[1]);
                System.out.println("move is " + move.isCapture() + " capture, there is a forced one");
                return false;
            } else if (hasToCapture(Globals.machine.isTurnOf()) && !move.isCapture())
                return false;

            System.out.println("no capture/forced capture");

            executeMove(move);

            held = null;

            return true;
        }
        return false;
    }

    private boolean isValid(Move move) {
        // move object internally invalid
        if (move.from == null || move.to == null)
            return false;
        if (move.from.length != 2 || move.to.length != 2)
            return false;

        // move to a field outside the board
        if (!Helpers.inBoard(move.from) || !Helpers.inBoard(move.to))
            return false;

        // move length not proper
        if (!isProperLength(move))
            return false;

        // move backwards as not-king
        if (!isProperDirection(move))
            return false;

        // move to an occupied field
        if (getPiece(move.to) != null)
            return false;

        return true;
    }

    private boolean isProperLength(Move move) {
        int c_len = move.isCapture() ? 2 : 1;
        return (move.lengthX() == c_len && move.lengthY() == c_len);
    }

    private boolean isProperDirection(Move move) {
        return (move.ofWhite && move.from[1] < move.to[1]) || (!move.ofWhite && move.from[1] > move.to[1])
                || getPiece(move.from).isKing();
    }

    private void capture(Piece captured) {
        if (captured == null)
            return;
        captured.hide();
        pieces.remove(captured);
    }

    boolean sameField(int[] field1, int[] field2) {
        return (field1[0] == field2[0] && field1[1] == field2[1]);
    }
}