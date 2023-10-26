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
    GameState state = GameState.MOVING;
    
    boolean playingWhite;

	private Stage stage;
    Menu menu = new Menu();
    Piece held;

    public StateMachine() {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);	
    }

    ArrayList<Move> moveList;

    public void update() {
        if (state != GameState.MENU) {
            if (Gdx.input.justTouched()) {
                int x = Gdx.input.getX() / 60;
                int y = 7 - Gdx.input.getY() / 60;
                if (!playingWhite) { y = 7 - y; x = 7 - x; }

                String to = Helpers.convertCords(x, y);
                Piece field = Globals.board.getField(x, y);
                
                if (field != null && field.isWhite() == playingWhite) { 
                    held = Globals.board.getField(x, y);
                }
                else if (held != null) executeMove(new Move(Helpers.convertCords(held.GridX(), held.GridY()) + to, playingWhite));
            }
            return;
         }
        if (menu.update()) {
            state = GameState.MOVING;
            newGame(true);
        }
    }
    public void draw() {
        if (state != GameState.MENU) {
            stage.draw();
            return;
        }
    }
    public void newGame(boolean white) {
        Globals.board = new Board(white);
        stage.addActor(Globals.board);
        playingWhite = white;
        for (Piece piece : Globals.board.pieces) {
            stage.addActor(piece);
        }
        moveList = new ArrayList<Move>();
    }
    public boolean executeMove(Move move) {
        if (Globals.board.executeMove(move)) {
            if ((playingWhite && move.to[1] == 7) || (!playingWhite && move.to[1] == 0)) {
                Globals.board.getField(move.to).kingMe();
            }
            moveList.add(move);
            return true;
        }
        return false;
    }

	public void dispose () {
		stage.dispose();
	}
}
