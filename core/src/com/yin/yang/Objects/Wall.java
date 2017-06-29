package com.yin.yang.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.yin.yang.Manager.TextureManager;

/**
 * Created by Seminar on 6/26/2017.
 */

public class Wall {
    Sprite sprite;
    public Rectangle wallBox;

    public Wall(float x, float y, float w, float h, Texture text){
        sprite = new Sprite(text);
        sprite.setPosition(x, y);
        sprite.setSize(w,h);
        wallBox = new Rectangle(sprite.getX(), sprite.getY(), w, h);
    }

    public void draw(SpriteBatch b){
        sprite.draw(b);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        wallBox.setPosition(x, y);
    }

    public void moveUp(){
        setPosition(sprite.getX(), sprite.getY() + 100 * Gdx.graphics.getDeltaTime());
    }

    public void moveDown(){
        setPosition(sprite.getX(), sprite.getY() - 100 * Gdx.graphics.getDeltaTime());
    }

    public void moveLeft() {
        setPosition(sprite.getX() - 100 * Gdx.graphics.getDeltaTime(), sprite.getY());
    }

    public void moveRight() {
        setPosition(sprite.getX() + 100 * Gdx.graphics.getDeltaTime(), sprite.getY());
    }
}
