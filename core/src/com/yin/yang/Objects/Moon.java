package com.yin.yang.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.yin.yang.Manager.TextureManager;

/**
 * Created by Seminar on 6/28/2017.
 */

public class Moon {
    Sprite sprite;
    public Rectangle moonBox;

    public Moon(float x, float y) {
        sprite = new Sprite(TextureManager.moonTexture);
        sprite.setSize(25, 35);
        sprite.setPosition(x, y);
        moonBox = new Rectangle(sprite.getX() + 1, sprite.getY() + 1, sprite.getWidth() - 2, sprite.getHeight() - 2);
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }

}
