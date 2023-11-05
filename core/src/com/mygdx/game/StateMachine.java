package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.ui.Menu;
import com.mygdx.game.ui.OnlineMenu;
import com.mygdx.game.ui.StartMenu;

public class StateMachine {
    enum GameState {
        START_MENU,
        ONLINE_MENU,
        MOVING,
        AWATING_ENEMY_MOVE,
        WHITE_WON,
        BLACK_WON,
        DRAW
    }

    Board board;
    Network network;

    GameState state;

    boolean playingWhiteOnline;
    boolean onlineGame;

    boolean turnWhiteLocal;

    Menu start;
    Menu online;

    ArrayList<Move> moveList;

    StateMachine() {

        start = new StartMenu();
        online = new OnlineMenu();

        network = new Network();

        state = GameState.START_MENU;
        
        Gdx.input.setInputProcessor(start);
    }

    void update() {
        if ((state == GameState.MOVING || state == GameState.AWATING_ENEMY_MOVE) && board.isGameOver()) {
            state = board.getWinner() ? GameState.WHITE_WON : GameState.BLACK_WON;
            System.out.println("GAME ENDED, " + state.toString());
            return;
        }
        if ((state == GameState.MOVING || state == GameState.AWATING_ENEMY_MOVE) && board.isDraw()) {
            state = GameState.DRAW;
            System.out.println("GAME ENDED WITH A DRAW");
            return;
        }

        switch (state) {
            case START_MENU:
                break;
            case ONLINE_MENU:
                break;
            case MOVING:
              if (!Gdx.input.isTouched())
                  break;

              int x = Gdx.input.getX() / 100;
              int y = 7 - Gdx.input.getY() / 100;

              if (drawBlackDown()) {
                  y = 7 - y;
                  x = 7 - x;
              }

              if (!board.handleClick(x, y))
                  break;

                if (onlineGame)
                    network.sendMove(lastMove());

                if (isForcedCapture())
                    break;

                if (onlineGame)
                    MyGdxGame.machine.state = GameState.AWATING_ENEMY_MOVE;
                else
                    turnWhiteLocal = !turnWhiteLocal;

                break;
            case AWATING_ENEMY_MOVE:
                if (network.connectedSocket == null)
                    break;

                Move enemyMove = network.recieveMove();
                if (enemyMove == null)
                    break;

                board.executeMove(enemyMove);

                if (isForcedCapture())
                    break;

                MyGdxGame.machine.state = GameState.MOVING;

                break;
            default:
                break;
        }
        return;
    }

    void draw() {
        switch (state) {
            case START_MENU:
                start.draw();
                break;
            case ONLINE_MENU:
                online.draw();
                break;
            default:
                board.draw();
                break;
        }
    }

    Move lastMove() {
        if (moveList.size() == 0)
            return null;
        return moveList.get(moveList.size() - 1);
    }

    boolean isTurnOf() {
        if (onlineGame)
            return playingWhiteOnline;
        return turnWhiteLocal;
    }

    private void initializeGame() {
        board = new Board();
        state = (onlineGame ? playingWhiteOnline : true) ? GameState.MOVING : GameState.AWATING_ENEMY_MOVE;
        Gdx.input.setInputProcessor(board);
        moveList = new ArrayList<Move>();
    }

    public void newLocalGame() {
        onlineGame = false;
        turnWhiteLocal = true;

        initializeGame();
    }

    public void hostOnlineGame(boolean white) {
        onlineGame = true;
        network.connect("127.0.0.1", white);
        playingWhiteOnline = white;

        initializeGame();
    }
    public void joinOnlineGame() {
        onlineGame = true;
        network.connect("127.0.0.1");
        playingWhiteOnline = network.isWhite;

        initializeGame();
    }
    public void toOnlineMenu() {
        state = GameState.ONLINE_MENU;
        Gdx.input.setInputProcessor(online);
    }
    public void toStartMenu() {
        System.out.println("exiting game");
        state = GameState.START_MENU;
        Gdx.input.setInputProcessor(start);
        network = null;
        board = null;
    }

    boolean isForcedCapture() {
        if (lastMove() == null)
            return false;
        if (lastMove().hasKinged)
            return false;
        return lastMove().isCapture() && board.hasToCapture(board.getPiece(lastMove().to));
    }
    boolean drawBlackDown() {
        if (onlineGame && !playingWhiteOnline) return true;
        return false;
    }
}
