package com.yin.yang.Manager;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by seminar on 6/27/2017.
 */

public class TextureManager {
    public static Texture backTexture;
    public static Texture wWallTexture;
    public static Texture bWallTexture;
    public static Texture sunTexture;
    public static Texture moonTexture;

    public static void loadGame(){
        backTexture = new Texture("background.png");
        wWallTexture = new Texture("wwall.png");
        bWallTexture = new Texture("bwall.png");
        sunTexture = new Texture("sun.png");
        moonTexture = new Texture("moon.png");
    }

    public static void unloadGame(){
        backTexture.dispose();
        wWallTexture.dispose();
        bWallTexture.dispose();
        sunTexture.dispose();
        moonTexture.dispose();
    }
}
