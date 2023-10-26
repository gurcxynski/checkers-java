package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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


    Scene scene;
    ArrayList<Move> moveList;
    public void Update() {
        Move move = scene.Update();
        if (move != null) ExecuteMove(move);
    }
    public void Draw(SpriteBatch batch, HashMap<String, Texture> textures ) {

        if (state != GameState.MENU) {
            scene.Draw(batch, textures);
            return;
        }
        // Draw menu
    }
    public void NewGame(boolean white) {
        scene = new Scene(white);
        Globals.board = new Board();
        Globals.board.Initialize();
        moveList = new ArrayList<Move>();
    }
    public boolean ExecuteMove(Move move) {
        if (Globals.board.ExecuteMove(move)) {
            if ((scene.whitePlayer && move.to[1] == 7) || (!scene.whitePlayer && move.to[1] == 0)) {
                Globals.board.getField(move.to).kingMe();
            }
            moveList.add(move);
            return true;
        }
        return false;
    }
}
