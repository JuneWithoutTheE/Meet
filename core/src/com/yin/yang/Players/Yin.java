package com.yin.yang.Players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Seminar on 6/26/2017.
 */

public class Yin {
    Sprite sprite;
    Texture yin;
    public Rectangle yinBox;
    int speed = 150;

    public Yin(float x, float y) {
        yin = new Texture("yin.png");
        sprite = new Sprite(yin);
        sprite.setSize(37, 50);
        sprite.setPosition(x, y);
        yinBox = new Rectangle(sprite.getX() + 2, sprite.getY() + 2, sprite.getWidth() - 4, sprite.getHeight() - 4);
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        yinBox.setPosition(x, y);
    }

    public void moveDown() {
        setPosition(sprite.getX(), sprite.getY() - speed * Gdx.graphics.getDeltaTime());
    }

    public void moveUp() {
        setPosition(sprite.getX(), sprite.getY() + speed * Gdx.graphics.getDeltaTime());
    }

    public void moveLeft() {
        setPosition(sprite.getX() - speed * Gdx.graphics.getDeltaTime(), sprite.getY());
    }

    public void moveRight() {
        setPosition(sprite.getX() + speed * Gdx.graphics.getDeltaTime(), sprite.getY());
    }

    public void resetPosition(float x, float y) {

        setPosition(x, y);
    }

}


