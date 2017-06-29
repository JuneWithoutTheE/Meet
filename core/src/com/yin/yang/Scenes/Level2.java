package com.yin.yang.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.yin.yang.GameController;
import com.yin.yang.Manager.TextureManager;
import com.yin.yang.Objects.Moon;
import com.yin.yang.Objects.Sun;
import com.yin.yang.Objects.Wall;
import com.yin.yang.Players.Yang;
import com.yin.yang.Players.Yin;

import java.util.ArrayList;

/**
 * Created by seminar on 6/27/2017.
 */

public class Level2 implements Screen {
    GameController myGame;
    SpriteBatch batch;
    OrthographicCamera camera;
    ParticleEffect particle;
    BitmapFont white1, white2, black1, black2;

    ArrayList<Sun> sun;
    ArrayList<Moon> moon;
    Yin yin;
    Yang yang;
    ArrayList<Wall> whiteWall, blackWall;
    Rectangle center1, center2, leftWall, rightWall, topWall, bottomWall;
    float startXYin, startXYang, startYYin, startYYang;
    boolean particleEffectStarted, isCompleted;
    int timer, timer2, yinScore, yangScore;

    public Level2(GameController g) {
        myGame = g;
    }

    public void show() {
        TextureManager.loadGame();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        batch = new SpriteBatch();
        startXYin = 763;
        startXYang = 0;
        startYYin = 550;
        startYYang = 0;
        particle = new ParticleEffect();
        white1 = new BitmapFont(Gdx.files.internal("white.fnt"));
        white2 = new BitmapFont(Gdx.files.internal("white.fnt"));
        black1 = new BitmapFont(Gdx.files.internal("black.fnt"));
        black2 = new BitmapFont(Gdx.files.internal("black.fnt"));

        sun = new ArrayList<Sun>();
        sun.add(new Sun(297, 40));
        moon = new ArrayList<Moon>();
        moon.add(new Moon(468, 525));
        yin = new Yin(startXYin, startYYin);
        yang = new Yang(startXYang, startYYang);
        center1 = new Rectangle(387, 0, 1, 600);
        center2 = new Rectangle(412, 0, 1, 600);
        leftWall = new Rectangle(0, 0, 1, 600);
        rightWall = new Rectangle(800, 0, 5, 600);
        bottomWall = new Rectangle(0, 0, 800, 1);
        topWall = new Rectangle(0, 600, 800, 1);

        whiteWall = new ArrayList<Wall>();
        whiteWall.add(new Wall(350, 0, 50, 250, TextureManager.wWallTexture));
        whiteWall.add(new Wall(350, 350, 50, 250, TextureManager.wWallTexture));
        whiteWall.add(new Wall(175, 0, 100, 400, TextureManager.wWallTexture));
        whiteWall.add(new Wall(0, 500, 150, 100, TextureManager.wWallTexture));
        whiteWall.add(new Wall(0, 400, 50, 200, TextureManager.wWallTexture));
        whiteWall.add(new Wall(0, 125, 100, 200, TextureManager.wWallTexture));
        blackWall = new ArrayList<Wall>();
        blackWall.add(new Wall(400, 0, 50, 250, TextureManager.bWallTexture));
        blackWall.add(new Wall(400, 350, 50, 250, TextureManager.bWallTexture));
        blackWall.add(new Wall(517, 200, 100, 400, TextureManager.bWallTexture));
        blackWall.add(new Wall(650, 0, 150, 100, TextureManager.bWallTexture));
        blackWall.add(new Wall(750, 0, 50, 200, TextureManager.bWallTexture));
        blackWall.add(new Wall(700, 275, 100, 200, TextureManager.bWallTexture));

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
        if (timer < 500) {
            white1.draw(batch, "Collect the", 284, 310);
            black1.draw(batch, "suns and", 405, 310);
            white2.draw(batch, "mo", 365, 290);
            black2.draw(batch, "ons", 401, 290);
        }
        for (int i = 0; i < whiteWall.size(); i++) {
            whiteWall.get(i).draw(batch);
        }
        for (int j = 0; j < blackWall.size(); j++) {
            blackWall.get(j).draw(batch);
        }
        for (int i = 0; i < sun.size(); i++) {
            sun.get(i).draw(batch);
        }
        for (int i = 0; i < moon.size(); i++) {
            moon.get(i).draw(batch);
        }
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
        for (int i = 0; i < blackWall.size(); i++) {
            if (yin.yinBox.overlaps(blackWall.get(i).wallBox)) {
                timer = 0;
                yin.resetPosition(startXYin, startYYin);
                yang.resetPosition(startXYang, startYYang);
            }
        }

        //Prevents Yang from touching the walls
        for (int j = 0; j < whiteWall.size(); j++) {
            if (yang.yangBox.overlaps(whiteWall.get(j).wallBox)) {
                timer = 0;
                yang.resetPosition(startXYang, startYYang);
                yin.resetPosition(startXYin, startYYin);
            }
        }

        //Checks when Yin collects the moon
        for (int i = 0; i < moon.size(); i++) {
            if (yin.yinBox.overlaps(moon.get(i).moonBox)) {
                moon.remove(i);
                i--;
                yinScore++;
            }
        }

        //Checks when Yang collects the sun
        for (int i = 0; i < sun.size(); i++) {
            if (yang.yangBox.overlaps(sun.get(i).sunBox)) {
                sun.remove(i);
                i--;
                yangScore++;
            }
        }

        //Locks Yin and Yang position
        if (yinScore == 1 && yangScore == 1) {
            if (yang.yangBox.overlaps(yin.yinBox)) {
                yin.setPosition(387, 275);
                yang.setPosition(377, 275);
                newParticles(yin.yinBox.x + 10, yin.yinBox.y + 25);
                timer2++;
            }
        }

        //Changes screens
        if (isCompleted && timer2 > 75) {
            myGame.setScreen(new Level3(myGame));
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
