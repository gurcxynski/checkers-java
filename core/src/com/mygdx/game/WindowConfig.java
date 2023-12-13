package com.mygdx.game;

public abstract class WindowConfig {
    public static final String TITLE = "Checkers";
    public static final int INSIDE_SQUARE = 600;
    public static final int MARGIN = (int) (0.025 * INSIDE_SQUARE);
    public static final int OUTSIDE_SQUARE = INSIDE_SQUARE + 2 * MARGIN;
    public static final int SIZE = INSIDE_SQUARE / 8;
    public static final int BUTTON_DEFAULT_WIDTH = (int) (WindowConfig.INSIDE_SQUARE * 0.6);
    public static final int BUTTON_DEFAULT_HEIGHT = (int) (WindowConfig.INSIDE_SQUARE * 0.2);
    public static final int BUTTON_DEFAULT_X = (int) (WindowConfig.INSIDE_SQUARE * 0.2);
    public static final int BUTTON_DEFAULT_STEP = (int) (WindowConfig.INSIDE_SQUARE * 0.25);
    public static final int BUTTON_DEFAULT_PAD = 20;
}
