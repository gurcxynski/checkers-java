package com.mygdx.game.ui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Game;
import com.mygdx.game.WindowConfig;

public class MyButton extends Button {
    List<MyButton> disable;

    InputListener listenerFactory(final MyListener listener) {
        return new InputListener() {
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer,
                    int button) {
                return true;
            }

            public void touchUp(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer,
                    int button) {
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listener.onClick();
                    if (!isChecked())
                        setChecked(true);
                    if (disable != null) {
                        for (MyButton other : disable) {
                            other.setChecked(false);
                        }
                    }
                }
            }
        };
    }

    public void init(String texture_up, String texture_down, String texture_checked, MyListener listener) {
        Drawable up = createFilteredDrawable(texture_up);
        Drawable down = createFilteredDrawable(texture_down);
        Drawable checked = createFilteredDrawable(texture_checked);

        ButtonStyle style = new ButtonStyle();
        style.down = down;
        style.up = up;
        style.checked = checked;
        setStyle(style);
        addListener(listenerFactory(listener));
    }

    public void init(String textureName, MyListener listener) {
        Drawable texture = createFilteredDrawable(textureName);

        ButtonStyle style = new ButtonStyle();
        style.down = texture;
        style.up = texture;
        style.checked = texture;
        setStyle(style);
        addListener(listenerFactory(listener));
    }

    // for smoothing out the edges
    private Drawable createFilteredDrawable(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath + ".png"));

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    public MyButton(int tier, String texture_up, String texture_down, String texture_checked,
            final MyListener listener) {
        setBounds(
                WindowConfig.BUTTON_DEFAULT_X + WindowConfig.MARGIN,
                (WindowConfig.BUTTON_DEFAULT_STEP * tier) + WindowConfig.MARGIN,
                Game.skin.getDrawable(texture_up).getMinWidth(), Game.skin.getDrawable(texture_up).getMinHeight());
        init(texture_up, texture_down, texture_checked, listener);
    }

    public MyButton(int x, int y, String texture_up, String texture_down, String texture_checked,
            final MyListener listener) {
        setBounds(x + WindowConfig.MARGIN, y + WindowConfig.MARGIN, Game.skin.getDrawable(texture_up).getMinWidth(),
                Game.skin.getDrawable(texture_up).getMinHeight());
        init(texture_up, texture_down, texture_checked, listener);
    }

    public MyButton(int x, int y, int width, int height, String texture_up, String texture_down, String texture_checked,
            final MyListener listener) {
        setBounds(x + WindowConfig.MARGIN, y + WindowConfig.MARGIN, width, height);
        init(texture_up, texture_down, texture_checked, listener);
    }

    public MyButton(int x, int y, int size, String texture,
            final MyListener listener) {
        setBounds(x + WindowConfig.MARGIN, y + WindowConfig.MARGIN, size, size);
        init(texture, listener);
    }

    public void updateStyle() {
        setStyle(Game.skin.get("default", ButtonStyle.class));
    }
}
