package com.mygdx.game;

import java.io.Serializable;

public class Packet implements Serializable {
    Move move;
    boolean changeActivePlayer;

    public Packet(Move move, boolean changeActivePlayer) {
        this.move = move;
        this.changeActivePlayer = changeActivePlayer;
    }
}
