package com.mygdx.game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ui.EndGameMenu;
import com.mygdx.game.ui.StartMenu;

/**
 * The StateMachine class represents the state machine for the game.
 * It manages the game state, player turns, network communication, and stage transitions.
 */
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

    public Board ret_board = null;
    GameState ret_state = null;

    ArrayList<Move> moveList;

    StateMachine() {

        network = new Network();

        activeStage = new StartMenu();

        state = GameState.MENU;

        Gdx.input.setInputProcessor(activeStage);
    }

    /**
     * Checks for incoming moves from the network.
     * If there is a connected socket, it receives a move from the network.
     * If the received move is not null, it executes the move on the active stage.
     * If there is no forced capture, it sets the game state to AWAITING_LOCAL.
     * If the active stage is game over, it ends the game.
     */
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
        if (ret_board != null) ret_board.draw();
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
    /**
     * Sets the active stage to the specified menu class and changes the game state to MENU.
     * Also sets the input processor to the active stage.
     *
     * @param menuClass the class of the menu stage to set as the active stage
     * @param <T>       the type of the menu stage
     */
    public <T extends Stage> void toMenu(Class<T> menuClass) {
        try {
            activeStage = menuClass.getDeclaredConstructor().newInstance();
            state = GameState.MENU;
            Gdx.input.setInputProcessor(activeStage);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public void hold_board() {
        ret_board = (Board) activeStage;
        ret_state = state;
    }

    public void resume() {
        activeStage = ret_board;
        state = ret_state;
        ret_board = null;
        ret_state = null;
        Gdx.input.setInputProcessor(activeStage);
    }

    public void endGame() {
        hold_board();
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
