package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StateMachine {
    public enum GameState {
        MENU,
        MOVING,
        AWATING_ENEMY_MOVE,
        WHITE_WON,
        BLACK_WON,
        DRAW
    }

    GameState state = GameState.MENU;

    boolean playingWhite;
    boolean turnWhite;

    Stage stage;
    Menu menu;
    Piece held;

    public StateMachine() {
        stage = new Stage(new ScreenViewport());
        menu = new Menu();
        Gdx.input.setInputProcessor(stage);
    }
    
    ArrayList<Move> moveList;

    public void update() {
        if (state == GameState.MOVING && Globals.board.isDraw()) {
            state = GameState.DRAW;
        }
        
        if (state != GameState.MENU) {
            if (Gdx.input.justTouched()) {
                int x = Gdx.input.getX() / 60;
                int y = 7 - Gdx.input.getY() / 60;
                if (!playingWhite) {
                    y = 7 - y;
                    x = 7 - x;
                }

                String to = Helpers.convertCords(x, y);
                Piece field = Globals.board.getField(x, y);

                if (field != null /* && field.isWhite() == playingWhite */) {
                    held = Globals.board.getField(x, y);
                } else if (held != null) {
                    executeMove(new Move(Helpers.convertCords(held.GridX(), held.GridY()) + to,
                            /* playingWhite */turnWhite), true);
                    held = null;
                    return;
                }
            }
            return;
        }
        menu.update();
        return;
    }

    public void draw() {
        if (state != GameState.MENU) {
            stage.draw();
            return;
        }
        menu.draw(stage.getBatch());
    }
    public void newGame(boolean white, boolean online, boolean isServer) {
        if (online) return;
        Globals.network = new Network(isServer);
        state = GameState.MOVING;
        Globals.board = new Board(white);
        stage.addActor(Globals.board);
        playingWhite = white;
        turnWhite = true;
        for (Piece piece : Globals.board.pieces) {
            stage.addActor(piece);
        }
        moveList = new ArrayList<Move>();
    }

    public boolean executeMove(Move move, boolean changeActivePlayer) {
        if (Globals.board.executeMove(move)) {
            moveList.add(move);
            Globals.network.sendMove(move, changeActivePlayer);
            if (!Helpers.mustCapture(turnWhite)) turnWhite = !turnWhite;
            return true;
        }
        return false;
    }

    public void dispose() {
        stage.dispose();
    }
}
