package com.yin.yang.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.yin.yang.GameController;
import com.yin.yang.Manager.TextureManager;
import com.yin.yang.Objects.Wall;
import com.yin.yang.Players.Yang;
import com.yin.yang.Players.Yin;

import java.util.ArrayList;

/**
 * Created by Seminar on 6/26/2017.
 */

public class Level1 implements Screen {
    GameController myGame;
    SpriteBatch batch;
    OrthographicCamera camera;
    ParticleEffect particle;
    BitmapFont white, black;

    Yin yin;
    Yang yang;
    Wall whiteWall1, whiteWall2, blackWall1, blackWall2;
    Rectangle center1, center2, leftWall, rightWall, topWall, bottomWall;
    float startXYin, startXYang, startY;
    boolean particleEffectStarted, isCompleted;
    int timer, timer2;

    public Level1(GameController g) {
        myGame = g;
    }

    public void show() {
        TextureManager.loadGame();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        batch = new SpriteBatch();
        white = new BitmapFont(Gdx.files.internal("white.fnt"));
        black = new BitmapFont(Gdx.files.internal("black.fnt"));

        startXYin = 763;
        startXYang = 0;
        startY = 260;
        particle = new ParticleEffect();

        yin = new Yin(startXYin, startY);
        yang = new Yang(startXYang, startY);
        center1 = new Rectangle(387, 0, 1, 600);
        center2 = new Rectangle(412, 0, 1, 600);
        leftWall = new Rectangle(0, 0, 1, 600);
        rightWall = new Rectangle(800, 0, 5, 600);
        bottomWall = new Rectangle(0, 0, 800, 1);
        topWall = new Rectangle(0, 600, 800, 1);
        whiteWall1 = new Wall(GameController.SCREEN_WIDTH / 6, 0, 25, GameController.SCREEN_HEIGHT * 2 / 3, TextureManager.wWallTexture);
        whiteWall2 = new Wall(GameController.SCREEN_WIDTH / 3, GameController.SCREEN_HEIGHT / 3, 25, GameController.SCREEN_HEIGHT * 2 / 3, TextureManager.wWallTexture);
        blackWall1 = new Wall(GameController.SCREEN_WIDTH * 5 / 6, GameController.SCREEN_HEIGHT / 3, 25, GameController.SCREEN_HEIGHT * 2 / 3, TextureManager.bWallTexture);
        blackWall2 = new Wall(GameController.SCREEN_WIDTH * 2 / 3, 0, 25, GameController.SCREEN_HEIGHT * 2 / 3, TextureManager.bWallTexture);

        particle.load(Gdx.files.internal("particle.p"), Gdx.files.internal("particles"));
        particle.allowCompletion();
        particleEffectStarted = false;
    }

    public void newParticles(float x, float y) {
        if (!particleEffectStarted) {
            particle.setPosition(x, y);
            particle.start();
        }
        particleEffectStarted = true;
        isCompleted = true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        //draw
        batch.begin();
        batch.draw(TextureManager.backTexture, 0, 0);
        if (timer < 250) {
            white.draw(batch, "Avoid touching", 231, 195);
            black.draw(batch, "the barriers", 405, 195);
        }
        whiteWall1.draw(batch);
        whiteWall2.draw(batch);
        blackWall1.draw(batch);
        blackWall2.draw(batch);
        particle.draw(batch, Gdx.graphics.getDeltaTime());
        yin.draw(batch);
        yang.draw(batch);
        batch.end();
        timer++;

        //Yin movement
        if (timer > 50) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                yin.moveLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                yin.moveRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                yin.moveUp();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yin.moveDown();
            }

            //Yang movement
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                yang.moveLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                yang.moveRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                yang.moveUp();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                yang.moveDown();
            }
        }

        //Set boundaries for Yin
        if (yin.yinBox.overlaps(center1)) {
            yin.setPosition((float) center1.getX() + center1.width, yin.yinBox.getY());
        }
        if (yin.yinBox.overlaps(leftWall)) {
            yin.setPosition((float) leftWall.getX() + leftWall.width, yin.yinBox.getY());
        }
        if (yin.yinBox.overlaps(rightWall)) {
            yin.setPosition((float) rightWall.getX() - yin.yinBox.width, yin.yinBox.getY());
        }
        if (yin.yinBox.overlaps(topWall)) {
            yin.setPosition(yin.yinBox.getX(), (float) topWall.getY() - yin.yinBox.height);
        }
        if (yin.yinBox.overlaps(bottomWall)) {
            yin.setPosition(yin.yinBox.getX(), (float) bottomWall.getY() + bottomWall.height);
        }

        //Set boundaries for Yang
        if (yang.yangBox.overlaps(center2)) {
            yang.setPosition((float) center2.getX() - yang.yangBox.width, yang.yangBox.getY());
        }
        if (yang.yangBox.overlaps(leftWall)) {
            yang.setPosition((float) leftWall.getX() + leftWall.width, yang.yangBox.getY());
        }
        if (yang.yangBox.overlaps(rightWall)) {
            yin.setPosition((float) rightWall.getX() - yang.yangBox.width, yang.yangBox.getY());
        }
        if (yang.yangBox.overlaps(topWall)) {
            yang.setPosition(yang.yangBox.getX(), (float) topWall.getY() - yang.yangBox.height);
        }
        if (yang.yangBox.overlaps(bottomWall)) {
            yang.setPosition(yang.yangBox.getX(), (float) bottomWall.getY() + bottomWall.height);
        }

        //Prevents Yin from touching the walls
        if (yin.yinBox.overlaps(blackWall1.wallBox) || yin.yinBox.overlaps(blackWall2.wallBox)) {
            timer = 0;
            yin.resetPosition(startXYin, startY);
            yang.resetPosition(startXYang, startY);
        }

        //Prevents Yang from touching the walls
        if (yang.yangBox.overlaps(whiteWall1.wallBox) || yang.yangBox.overlaps(whiteWall2.wallBox)) {
            timer = 0;
            yang.resetPosition(startXYang, startY);
            yin.resetPosition(startXYin, startY);
        }

        //Locks Yin and Yang position
        if (yang.yangBox.overlaps(yin.yinBox)) {
            yin.setPosition(387, 275);
            yang.setPosition(377, 275);
            newParticles(yin.yinBox.x + 10, yin.yinBox.y + 25);
            timer2++;
        }

        //Changes screens
        if(isCompleted && timer2 > 75){
            myGame.setScreen(new Level2(myGame));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            myGame.setScreen(new MainMenu(myGame));
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
        batch.dispose();
        TextureManager.unloadGame();
    }
}
