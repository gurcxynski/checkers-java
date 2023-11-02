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

    GameState state;

    boolean playingWhiteOnline;
    boolean onlineGame;

    boolean turnWhiteLocal;

    Stage stage;

    StartMenu start;
    OnlineMenu online;

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
                if (!Gdx.input.isTouched())
                    return;

                int x = Gdx.input.getX() / 100;
                int y = 7 - Gdx.input.getY() / 100;
                
                if (drawBlackDown()) {
                    y = 7 - y;
                    x = 7 - x;
                }

                if (!Globals.board.handleClick(x, y))
                    break;

                if (onlineGame)
                    Globals.network.sendMove(lastMove());

                if (isForcedCapture())
                    break;

                if (onlineGame)
                    Globals.machine.state = GameState.AWATING_ENEMY_MOVE;
                else
                    turnWhiteLocal = !turnWhiteLocal;

                break;
            case AWATING_ENEMY_MOVE:
                if (Globals.network.connectedSocket == null)
                    break;

                Move enemyMove = Globals.network.recieveMove();
                if (enemyMove == null)
                    break;

                Globals.board.executeMove(enemyMove);

                if (isForcedCapture())
                    break;

                Globals.machine.state = GameState.MOVING;

                break;
            case WHITE_WON:
                toStartMenu();
                break;
            case BLACK_WON:
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

    Move lastMove() {
        if (moveList.size() == 0)
            return null;
        return moveList.get(moveList.size() - 1);
    }

    public boolean isTurnOf() {
        if (onlineGame)
            return playingWhiteOnline;
        return turnWhiteLocal;
    }

    private void initializeGame(boolean white) {
        Globals.board = new Board();
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

    public void toOnlineMenu() {
        state = GameState.ONLINE_MENU;
    }

    public void toStartMenu() {
        System.out.println("exiting game");
        state = GameState.START_MENU;
        Globals.network = null;
        Globals.board = null;
    }

    public void dispose() {
        stage.dispose();
    }

    boolean isForcedCapture() {
        if (lastMove() == null)
            return false;
        if (lastMove().hasKinged)
            return false;
        return lastMove().isCapture() && Globals.board.hasToCapture(Globals.board.getPiece(lastMove().to));
    }
    public boolean drawBlackDown() {
        if (onlineGame && !playingWhiteOnline) return true;
        if (!turnWhiteLocal) return true;
        return false;
    }
}
