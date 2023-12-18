package com.mygdx.game;

public final class WindowConfig {
    public static final String TITLE = "Checkers";
    public static final int OUTSIDE_SQUARE = 750;
    public static final int MARGIN = (int) (0.04 * OUTSIDE_SQUARE);
    public static final int INSIDE_SQUARE = OUTSIDE_SQUARE - 2 * MARGIN;
    public static final int SIZE = INSIDE_SQUARE / 8;
    public static final int BUTTON_DEFAULT_WIDTH = (int) (WindowConfig.INSIDE_SQUARE * 0.65);
    public static final int BUTTON_DEFAULT_HEIGHT = (int) (WindowConfig.INSIDE_SQUARE * 0.2);
    public static final int BUTTON_DEFAULT_X = (int) (WindowConfig.INSIDE_SQUARE * 0.2);
    public static final int BUTTON_DEFAULT_STEP = (int) (WindowConfig.INSIDE_SQUARE * 0.25);
    public static final int BUTTON_DEFAULT_PAD = (int) (WindowConfig.INSIDE_SQUARE * 0.025);
    public static final int FONT_SIZE_NORMAL = 42;
    public static final int FONT_SIZE_HEADER = 88;
}
