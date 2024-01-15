package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ui.MyButton;
import com.mygdx.game.ui.MyListener;
import com.mygdx.game.ui.PauseMenu;

/**
 * The Board class represents the game board in a checkers game.
 * It extends the Stage class and contains methods for initializing the board,
 * executing moves, capturing pieces, checking for game over conditions, and
 * more.
 */
public class Board extends Stage {
    ArrayList<Piece> pieces;
    Piece held;

    public Board() {
        initialize();
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int gridX = (int) (Game.machine.drawBlackDown()
                        ? ((WindowConfig.OUTSIDE_SQUARE - x - WindowConfig.MARGIN) / WindowConfig.SIZE)
                        : ((x - WindowConfig.MARGIN) / WindowConfig.SIZE));
                int gridY = (int) (Game.machine.drawBlackDown()
                        ? ((WindowConfig.OUTSIDE_SQUARE - y - WindowConfig.MARGIN) / WindowConfig.SIZE)
                        : ((y - WindowConfig.MARGIN) / WindowConfig.SIZE));

                if (!Helpers.inBoard(new int[] { gridX, gridY }))
                    return true;

                String clickedField = Helpers.convertCords(gridX, gridY);
                Piece clickedPiece = getPiece(gridX, gridY);

                if (Game.machine.onlineGame && Game.machine.turnWhiteLocal != Game.machine.playingOnlineWhite)
                    return true;

                // pick up a piece
                if (clickedPiece != null && clickedPiece.isWhite() == Game.machine.isWhiteTurn()) {
                    held = getPiece(gridX, gridY);
                    return true;
                }
                // execute a move
                if (!(held != null && clickedPiece == null))
                    return true;
                Move move = new Move(held.getFieldString() + clickedField, Game.machine.isWhiteTurn());

                if (!isValid(move))
                    return true;

                // force captures
                Move last = Game.machine.lastMove();
                if (last != null && !last.hasKinged && last.ofWhite == Game.machine.isWhiteTurn()
                        && !(sameField(move.from, last.to) && move.isCapture())) {
                    return true;
                } else if (hasToCapture(Game.machine.isWhiteTurn()) && !move.isCapture())
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

    /**
     * Initializes the board by creating and adding pieces to the board.
     * Also adds a button for and accessing options.
     */
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
        // pieces.add(new Piece(0, 0, true));
        // pieces.add(new Piece(2, 2, false));
        for (Actor piece : pieces) {
            addActor(piece);
        }
        addActor(new MyButton(
                (int) (WindowConfig.OUTSIDE_SQUARE * 0.9 - WindowConfig.MARGIN),
                (int) (WindowConfig.OUTSIDE_SQUARE * 0.9 - WindowConfig.MARGIN),
                (WindowConfig.OUTSIDE_SQUARE / 10), "settings_icon_transparent",
                new MyListener() {
                    public void onClick() {
                        Game.machine.hold_board();
                        Game.machine.toMenu(PauseMenu.class);
                    }
                }));
    }

    @Override
    public void draw() {
        getBatch().begin();
        Color color = Styles.getBackgroundColor(Game.style);
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                getBatch().draw(Game.skin.get("chessboard" + Game.style, Texture.class),
                        i * (WindowConfig.SIZE * 2) + WindowConfig.MARGIN,
                        j * (WindowConfig.SIZE * 2) + WindowConfig.MARGIN, (WindowConfig.SIZE * 2),
                        (WindowConfig.SIZE * 2));                
            }
        }
        getBatch().end();
        super.draw();
    }

    public boolean isDraw() {
        return false;
    }

    public boolean isGameOver() {
        boolean color = (pieces.get(0)).isWhite();
        for (Piece piece : pieces) {
            if (piece.isWhite() != color)
                return false;
        }
        return true;
    }

    public boolean getWinner() {
        return (pieces.get(0)).isWhite();
    }

    boolean hasToCapture(boolean white) {
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
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

    /**
     * Checks if a given move is valid on the board.
     *
     * @param move the move to be checked
     * @return true if the move is valid, false otherwise
     */
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

    public void updateColor() {
        for (Piece piece : pieces) {
            piece.updateColor();
        }
    }
}