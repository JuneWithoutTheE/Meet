package com.yin.yang.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yin.yang.GameController;

/**
 * Created by Seminar on 6/26/2017.
 */

public class MainMenu implements Screen {
    GameController myGame;
    Texture text;
    SpriteBatch batch;
    OrthographicCamera camera;
    BitmapFont yinYang, instr;

    public MainMenu(GameController g) {
        myGame = g;
    }

    public void show() {
        batch = new SpriteBatch();
        text = new Texture(Gdx.files.internal("menu.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        yinYang = new BitmapFont(Gdx.files.internal("meet.fnt"));
        instr = new BitmapFont(Gdx.files.internal("instr.fnt"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(text, 0, 0);
        yinYang.draw(batch, "MEET", 163, 375);
        instr.draw(batch, "Press SPACE to play", 200, 50);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            myGame.setScreen(new Level0(myGame));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            myGame.setScreen(new Level1(myGame));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            myGame.setScreen(new Level2(myGame));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            myGame.setScreen(new Level3(myGame));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            myGame.setScreen(new Level4(myGame));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            myGame.setScreen(new Level5(myGame));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
