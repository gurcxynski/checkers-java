package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.StateMachine.GameState;

public class EndGameScreen {

    public void draw(Batch batch) {
        Texture toDraw = null;
        batch.begin();
        StateMachine machine = Globals.machine;
        if (machine.state == GameState.DRAW) toDraw = Globals.textures.get("draw");

        if (machine.onlineGame && machine.state == GameState.WHITE_WON) {
            toDraw = machine.playingWhiteOnline ? Globals.textures.get("youwon") : Globals.textures.get("youlost");
        }

        if (!machine.onlineGame) {
            toDraw = machine.state == GameState.WHITE_WON ? Globals.textures.get("whitewon") : Globals.textures.get("blackwon");
        }
        int x = (Gdx.graphics.getWidth() - toDraw.getWidth()) / 2;
        int y = (Gdx.graphics.getHeight() - toDraw.getHeight()) / 2;
        batch.draw(toDraw, x, y);
        batch.end();
    }
    public void update() {
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Globals.machine.toStartMenu();
        }
    }
}
