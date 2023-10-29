package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StateMachine {
    public enum GameState {
        START_MENU,
        ONLINE_MENU,
        MOVING,
        AWATING_ENEMY_MOVE,
        WHITE_WON,
        BLACK_WON,
        DRAW
    }

    GameState state = GameState.START_MENU;

    boolean playingWhiteOnline;
    boolean turnWhiteLocal;
    boolean onlineGame;

    Stage stage;
    StartMenu start;
    OnlineMenu online;
    Piece held;

    public StateMachine() {
        stage = new Stage(new ScreenViewport());
        start = new StartMenu();
        online = new OnlineMenu();
        Gdx.input.setInputProcessor(stage);
    }
    
    ArrayList<Move> moveList;

    public void update() {
        switch (state) {
            case START_MENU:
                start.update();
                break;
            case ONLINE_MENU:
                online.update();
                break;
            case MOVING:
                if (!onlineGame && Gdx.input.justTouched()) {
                    int x = Gdx.input.getX() / 60;
                    int y = 7 - Gdx.input.getY() / 60;
                    if (!playingWhiteOnline) {
                        y = 7 - y;
                        x = 7 - x;
                    }
                
                    String to = Helpers.convertCords(x, y);
                    Piece field = Globals.board.getField(x, y);
                
                    if (field != null && field.isWhite() == turnWhiteLocal) {
                        held = Globals.board.getField(x, y);
                    } else if (held != null) {
                        executeMove(new Move(Helpers.convertCords(held.GridX(), held.GridY()) + to, turnWhiteLocal));
                        held = null;
                    }
                }
                if (onlineGame && Gdx.input.justTouched()) {
                    int x = Gdx.input.getX() / 60;
                    int y = 7 - Gdx.input.getY() / 60;
                    if (!playingWhiteOnline) {
                        y = 7 - y;
                        x = 7 - x;
                    }
                
                    String to = Helpers.convertCords(x, y);
                    Piece field = Globals.board.getField(x, y);
                
                    if (field != null && field.isWhite() == turnWhiteLocal) {
                        held = Globals.board.getField(x, y);
                    } else if (held != null) {
                        Move move = new Move(Helpers.convertCords(held.GridX(), held.GridY()) + to, playingWhiteOnline);
                        executeMove(move);
                        Globals.network.sendMove(move);
                        held = null;
                    }
                }
                break;
            case AWATING_ENEMY_MOVE:
                if (Globals.network.connectedSocket != null) {
                    Globals.network.recieveMove();
                    state = GameState.MOVING;
                }
                break;
            default:
                break;
        }
        return;
    }

    public void draw() {
        switch (state) {
            case START_MENU:
                start.draw(stage.getBatch());
                break;
            case ONLINE_MENU:
                online.draw(stage.getBatch());
                break;
            default:
                stage.draw();
                break;
        }
    }
    public void newLocalGame() {
        state = GameState.MOVING;
        onlineGame = false;
        Globals.board = new Board();
        stage.addActor(Globals.board);
        turnWhiteLocal = true;
        for (Piece piece : Globals.board.pieces) {
            stage.addActor(piece);
        }
        moveList = new ArrayList<Move>();
    }
    public void newOnlineGame(boolean white, boolean isServer) {
        Globals.network = new Network(isServer);
        state = white ? GameState.MOVING : GameState.AWATING_ENEMY_MOVE;
        onlineGame = true;
        Globals.board = new Board(white);
        stage.addActor(Globals.board);
        playingWhiteOnline = white;
        turnWhiteLocal = true;
        for (Piece piece : Globals.board.pieces) {
            stage.addActor(piece);
        }
        moveList = new ArrayList<Move>();
    }

    public boolean executeMove(Move move) {
        if (Globals.board.executeMove(move)) {
            moveList.add(move);
            if (onlineGame) Globals.network.sendMove(move);
            if (!Helpers.mustCapture(turnWhiteLocal)) turnWhiteLocal = !turnWhiteLocal;
            return true;
        }
        return false;
    }
    public void toOnlineMenu() {
        state = GameState.ONLINE_MENU;
    }
    public void dispose() {
        stage.dispose();
    }
}
