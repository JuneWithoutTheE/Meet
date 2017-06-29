package com.yin.yang;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yin.yang.Scenes.MainMenu;

public class GameController extends Game {
    GameController myGame;
    public final static float SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;

    public void create() {
        myGame = this;
        myGame.setScreen(new MainMenu(myGame));
    }

}
