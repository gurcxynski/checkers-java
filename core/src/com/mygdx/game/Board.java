package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.MyButtonCheck;
import com.mygdx.game.ui.StartMenu;

public class Board extends Stage {
    ArrayList<Piece> pieces;
    Piece held;

    public Board() {
        initialize();
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int gridX = (int) (Game.machine.drawBlackDown() ? ((800 - x) / 100) : (x / 100));
                int gridY = (int) (Game.machine.drawBlackDown() ? ((800 - y) / 100) : (y / 100));
                
                if (gridX < 0 || gridX > 7 || gridY < 0 || gridY > 7) return true;

                String clickedField = Helpers.convertCords(gridX, gridY);
                Piece clickedPiece = getPiece(gridX, gridY);

                System.out.println(clickedField);

                // pick up a piece
                if (clickedPiece != null && clickedPiece.isWhite() == Game.machine.isTurnOf()) {
                    held = getPiece(gridX, gridY);
                    return true;
                }
                // execute a move
                if (!(held != null && clickedPiece == null))
                    return true;
                Move move = new Move(held.getFieldString() + clickedField, Game.machine.isTurnOf());

                if (!isValid(move))
                    return true;

                // force captures
                Move last = Game.machine.lastMove();
                if (last != null && !last.hasKinged && last.ofWhite == Game.machine.isTurnOf()
                        && !(sameField(move.from, last.to) && move.isCapture())) {
                    return true;
                } else if (hasToCapture(Game.machine.isTurnOf()) && !move.isCapture())
                    return true;

                executeMove(move);

                held = null;
                Game.machine.onMoveExecuted();
                return true;
            }
        });
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

        if (move.captured) {
            capture(getPiece(new int[] { (move.from[0] + move.to[0]) / 2, (move.from[1] + move.to[1]) / 2 }));
        }

        // king the piece if it reached the end and is not king
        if (!getPiece(move.to).isKing() && (move.ofWhite && move.to[1] == 7) || (!move.ofWhite && move.to[1] == 0)) {
            getPiece(move.to).kingMe();
            move.hasKinged = true;
        }

        Game.machine.moveList.add(move);

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
        for (Actor piece : pieces) {
            addActor(piece);
        }
        if (Game.machine.onlineGame) addActor(new MyButton(0, 800, "forfeit", "light_tile", new InputListener() {
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    Game.machine.toMenu(new StartMenu());
                }
            }
        }));
    }

    @Override
    public void draw() {
        getBatch().begin();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                getBatch().draw(Game.skin.get((i + j) % 2 == 0 ? "light_tile" : "dark_tile", Texture.class),
                        i * 100, j * 100);
            }
        }
        getBatch().draw(Game.skin.get("light_tile", Texture.class), 0, 800, 800, 100);
        getBatch().end();
        super.draw();
    }

    public boolean isDraw() {
        return false;
    }

    public boolean isGameOver() {
        boolean color = ( pieces.get(0)).isWhite();
        for (Piece piece : pieces) {
            if (piece.isWhite() != color)
                return false;
        }
        return true;
    }

    public boolean getWinner() {
        return ( pieces.get(0)).isWhite();
    }

    boolean hasToCapture(boolean white) {
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece =  pieces.get(i);
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

    // return true if executed move

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
        pieces.remove(captured);
        captured.remove();
    }

    boolean sameField(int[] field1, int[] field2) {
        return (field1[0] == field2[0] && field1[1] == field2[1]);
    }

    public void pickUp(Piece piece) {
        held = piece;
    }
}