package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

    GameState state;

    boolean playingWhiteOnline;
    boolean onlineGame;

    boolean turnWhiteLocal;

    Stage stage;
    StartMenu start;
    OnlineMenu online;

    Piece held;

    ArrayList<Move> moveList;

    public StateMachine() {
        stage = new Stage(new ScreenViewport());
        start = new StartMenu();
        online = new OnlineMenu();
        state = GameState.START_MENU;
        Gdx.input.setInputProcessor(stage);
    }

    public void update() {
        if ((state == GameState.MOVING || state == GameState.AWATING_ENEMY_MOVE) && Globals.board.isGameOver()) {
            state = Globals.board.getWinner() ? GameState.WHITE_WON : GameState.BLACK_WON;
            System.out.println("GAME ENDED, " + state.toString());
        }
        switch (state) {
            case START_MENU:
                start.update();
                break;
            case ONLINE_MENU:
                online.update();
                break;
            case MOVING:
                if (Gdx.input.justTouched()) {
                    int x = Gdx.input.getX() / 100;
                    int y = 7 - Gdx.input.getY() / 100;

                    if (Helpers.drawRedDown()) {
                        y = 7 - y;
                        x = 7 - x;
                    }

                    String to = Helpers.convertCords(x, y);
                    Piece field = Globals.board.getField(x, y);

                    if (field != null && (!onlineGame || field.isWhite() == playingWhiteOnline)
                            && (onlineGame || field.isWhite() == turnWhiteLocal)) {
                        held = Globals.board.getField(x, y);
                    } else if (held != null) {
                        Move move = new Move(Helpers.convertCords(held.GridX(), held.GridY()) + to,
                                onlineGame ? playingWhiteOnline : turnWhiteLocal);
                        if (!executeMove(move))
                            return;
                        held = null;
                        if (onlineGame)
                            System.out.println((Globals.network.isServer ? "host " : "client ") + "executed move, ");
                        System.out.println(
                                "move " + move.toString() + (move.captured ? " is " : " is not ") + "a capture");
                        System.out
                                .println((Helpers.mustCaptureWith(move.to) ? "you can" : "you cant") + " capture more");
                        if (onlineGame && !(move.captured && Helpers.mustCaptureWith(move.to))) {
                            state = GameState.AWATING_ENEMY_MOVE;
                            System.out.println("setting AWATING_ENEMY_MOVE");
                        }
                    }
                }
                break;
            case AWATING_ENEMY_MOVE:
                if (Globals.network.connectedSocket != null) {
                    Move enemyMove = Globals.network.recieveMove();
                    if (enemyMove == null)
                        return;
                    Globals.machine.executeMove(enemyMove);
                    System.out
                            .println((Globals.network.isServer ? "host " : "client ") + "recieved and executed move,");
                    if (!(enemyMove.captured && Helpers.mustCaptureWith(enemyMove.to))) {
                        state = GameState.MOVING;
                        System.out.println("setting MOVING");
                    }
                }
                break;
            case WHITE_WON:
                if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
                    toStartMenu();
                break;
            case BLACK_WON:
                if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
                    toStartMenu();
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
            case WHITE_WON:
                stage.draw();
                break;
            case BLACK_WON:
                stage.draw();
                break;
            default:
                stage.draw();
                break;
        }
    }

    private void initializeGame(boolean white) {
        Globals.board = new Board(white);
        state = white ? GameState.MOVING : GameState.AWATING_ENEMY_MOVE;
        stage.addActor(Globals.board);
        for (Piece piece : Globals.board.pieces) {
            stage.addActor(piece);
        }
        moveList = new ArrayList<Move>();
    }

    public void newLocalGame(boolean white) {
        onlineGame = false;
        turnWhiteLocal = true;

        initializeGame(white);
    }

    public void newOnlineGame(boolean white, boolean isServer) {
        onlineGame = true;
        Globals.network = new Network(isServer, white);
        playingWhiteOnline = Globals.network.isWhite;

        initializeGame(playingWhiteOnline);
    }

    public boolean executeMove(Move move) {
        if (Globals.board.executeMove(move)) {
            moveList.add(move);
            if (onlineGame && move.ofWhite == playingWhiteOnline) {
                Globals.network.sendMove(move);
            }
            if (!onlineGame && !(move.captured && Helpers.mustCaptureWith(move.to)))
                turnWhiteLocal = !turnWhiteLocal;
            return true;
        }
        return false;
    }

    public void toOnlineMenu() {
        state = GameState.ONLINE_MENU;
    }

    public void toStartMenu() {
        System.out.println("exiting game");
        state = GameState.START_MENU;
        held = null;
        Globals.network = null;
    }

    public void dispose() {
        stage.dispose();
    }
}
