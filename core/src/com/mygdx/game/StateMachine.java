package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ui.OnlineMenu;
import com.mygdx.game.ui.StartMenu;

public class StateMachine {
    enum GameState {
        MENU,
        AWAITING_LOCAL,
        AWATING_NETWORK
    }

    Network network;

    GameState state;

    boolean playingWhiteOnline;
    boolean onlineGame;

    boolean turnWhiteLocal;

    Stage activeStage;

    ArrayList<Move> moveList;

    StateMachine() {

        network = new Network();

        activeStage = new StartMenu();

        state = GameState.MENU;

        Gdx.input.setInputProcessor(activeStage);
    }

    void checkForIncoming() {
        if (network.connectedSocket == null)
            return;

        Move enemyMove = network.recieveMove();
        if (enemyMove == null)
            return;

        ((Board) activeStage).executeMove(enemyMove);

        if (isForcedCapture())
            return;

        MyGdxGame.machine.state = GameState.AWAITING_LOCAL;
        return;
    }

    public void onMoveExecuted() {
        if (onlineGame)
            network.sendMove(lastMove());

        if (isForcedCapture())
            return;

        if (onlineGame)
            state = GameState.AWATING_NETWORK;
        else
            turnWhiteLocal = !turnWhiteLocal;
    }

    void draw() {
        activeStage.draw();
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
        activeStage = new Board();
        state = (onlineGame ? playingWhiteOnline : true) ? GameState.AWAITING_LOCAL : GameState.AWATING_NETWORK;
        Gdx.input.setInputProcessor(activeStage);
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
        activeStage = new OnlineMenu();
        state = GameState.MENU;
        Gdx.input.setInputProcessor(activeStage);
    }

    public void toStartMenu() {
        activeStage = new StartMenu();
        state = GameState.MENU;
        Gdx.input.setInputProcessor(activeStage);
        network = null;
    }

    boolean isForcedCapture() {
        if (lastMove() == null)
            return false;
        if (lastMove().hasKinged)
            return false;
        return lastMove().isCapture()
                && ((Board) activeStage).hasToCapture(((Board) activeStage).getPiece(lastMove().to));
    }

    boolean drawBlackDown() {
        if (onlineGame && !playingWhiteOnline)
            return true;
        return false;
    }
}
