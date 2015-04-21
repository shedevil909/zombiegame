package ru.drobina.zombiegame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyAssetManager {

    public static final AssetManager manager = new AssetManager();

    private static final String modelsPath = Gdx.files.internal("models/models.pack").toString();
    private static final String buttonPath = Gdx.files.internal("skins/buttons.pack").toString();
    private static final String skinPath = Gdx.files.internal("uiskin.json").toString();
    private static final String bgPath = Gdx.files.internal("bg.jpg").toString();
    private static final String pausePath = Gdx.files.internal("pause.png").toString();
    private static final String psyduckPath = Gdx.files.internal("psyduck.png").toString();
    
    private static final String redSoundPath = Gdx.files.internal("sounds/redSound.mp3").toString();
    private static final String whiteSoundPath = Gdx.files.internal("sounds/whiteSound.mp3").toString();
    private static final String cureSoundPath = Gdx.files.internal("sounds/cureSound.mp3").toString();
    private static final String redZombiSoundPath = Gdx.files.internal("sounds/redZombieSound.mp3").toString();
    private static final String fluSoundPath = Gdx.files.internal("sounds/fluSound.mp3").toString();
    private static final String birdfluSoundPath = Gdx.files.internal("sounds/birdfluSound.mp3").toString();
    private static final String ebolaSoundPath = Gdx.files.internal("sounds/ebolaSound.mp3").toString();
    private static final String hivSoundPath = Gdx.files.internal("sounds/hivSound.mp3").toString();
    private static final String fagSoundPath = Gdx.files.internal("sounds/fagSound.mp3").toString();
    private static final String medSoundPath = Gdx.files.internal("sounds/medicineSound.wav").toString();
    
    public static TextureAtlas modelsAtlas, buttonAtlas;
    public static Animation playerAnimation, cureAnimation;
    public static TextureRegion red, white, cure, redZombi, flu, birdflu, ebola, hiv, fag;
    public static Texture background, pause, psyduck;
    public static Skin skin;
    public static BitmapFont font;
    public static Sound redSound, whiteSound, cureSound, redZombiSound, fluSound, birdfluSound, ebolaSound, hivSound, fagSound, medSound;
    
    public static void load() {

        manager.load(modelsPath, TextureAtlas.class);
        manager.load(buttonPath, TextureAtlas.class);
        manager.load(skinPath, Skin.class);
        manager.load(bgPath, Texture.class);
        manager.load(pausePath, Texture.class);
        manager.load(psyduckPath, Texture.class);
        
        manager.load(redSoundPath, Sound.class);
        manager.load(whiteSoundPath, Sound.class);
        manager.load(cureSoundPath, Sound.class);
        manager.load(redZombiSoundPath, Sound.class);
        manager.load(fluSoundPath, Sound.class);
        manager.load(birdfluSoundPath, Sound.class);
        manager.load(ebolaSoundPath, Sound.class);
        manager.load(hivSoundPath, Sound.class);
        manager.load(fagSoundPath, Sound.class);
        manager.load(medSoundPath, Sound.class);
    }
   
    public static void create() {
        modelsAtlas = manager.get(modelsPath, TextureAtlas.class);
        buttonAtlas = manager.get(buttonPath, TextureAtlas.class);
        skin = manager.get(skinPath, Skin.class);
        skin.addRegions(modelsAtlas);
        skin.addRegions(buttonAtlas);
        background = manager.get(bgPath, Texture.class);
        pause = manager.get(pausePath, Texture.class);
        psyduck = manager.get(psyduckPath, Texture.class);
        
        redSound = manager.get(redSoundPath, Sound.class);
        whiteSound = manager.get(whiteSoundPath, Sound.class);
        cureSound = manager.get(cureSoundPath, Sound.class);
        redZombiSound = manager.get(redZombiSoundPath, Sound.class);
        fluSound = manager.get(fluSoundPath, Sound.class);
        birdfluSound = manager.get(birdfluSoundPath, Sound.class);
        ebolaSound = manager.get(ebolaSoundPath, Sound.class);
        hivSound = manager.get(hivSoundPath, Sound.class);
        fagSound = manager.get(fagSoundPath, Sound.class);
        medSound = manager.get(medSoundPath, Sound.class);
        
        playerAnimation = new Animation(0.2f, modelsAtlas.findRegions("zombi"));
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP);
        cureAnimation = new Animation(0.2f, modelsAtlas.findRegions("cure"));
        cureAnimation.setPlayMode(Animation.PlayMode.LOOP);
        
        red = skin.getRegion("red");
        white = skin.getRegion("white");
        cure = skin.getRegion("cure");
        redZombi = skin.getRegion("redZombi");
        flu = skin.getRegion("flu");
        birdflu = skin.getRegion("birdflu");
        ebola = skin.getRegion("ebola");
        hiv = skin.getRegion("hiv");
        fag = skin.getRegion("fag");
        
        font = skin.getFont("default-font");
    }
    
    public static void dispose() {
        manager.dispose();
    }
}
