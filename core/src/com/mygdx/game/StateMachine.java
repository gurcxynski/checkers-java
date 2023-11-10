package com.mygdx.game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ui.EndGameMenu;
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

    public Stage activeStage;

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

        Game.machine.state = GameState.AWAITING_LOCAL;

        if (((Board) activeStage).isGameOver())
            endGame();

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
        
        if (((Board) activeStage).isGameOver())
            endGame();
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
        network.connect(white);
        playingWhiteOnline = white;

        initializeGame();
    }

    public void joinOnlineGame(String ip) {
        onlineGame = true;
        network.connect(ip);
        playingWhiteOnline = network.isWhite;

        initializeGame();
    }
    public <T extends Stage> void toMenu(Class<T> menuClass) {
        try {
            activeStage = menuClass.getDeclaredConstructor().newInstance();
            state = GameState.MENU;
            Gdx.input.setInputProcessor(activeStage);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void endGame() {
        activeStage.draw();
        activeStage = new EndGameMenu(((Board) activeStage).getWinner());
        state = GameState.MENU;
        Gdx.input.setInputProcessor(activeStage);
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
