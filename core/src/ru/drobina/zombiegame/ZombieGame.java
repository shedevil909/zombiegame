package ru.drobina.zombiegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.drobina.zombiegame.gameobjects.Player;
import ru.drobina.zombiegame.gameworld.GameState;
import ru.drobina.zombiegame.helpers.GameConstants;
import ru.drobina.zombiegame.helpers.MyAssetManager;
import ru.drobina.zombiegame.screens.MenuScreen;

public class ZombieGame extends Game {

    private SpriteBatch sb;
    private Player player;
    private GameState state;

    private float score;
    private int level;
    private Preferences prefs;

    @Override
    public void create() {

        sb = new SpriteBatch();
        MyAssetManager.load();
        MyAssetManager.manager.finishLoading();
        MyAssetManager.create();

        prefs = Gdx.app.getPreferences("myPrefs");
        String s = prefs.getString("score", "36.600");
        score = Float.valueOf(s);
        level = prefs.getInteger("level", 0);

        player = new Player((Gdx.graphics.getWidth() - 32) / 2, 10, 64, 64);
        state = GameState.READY;

        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
        if (state == GameState.RUNNING) {
            player.update(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        savePrefs();
        sb.dispose();
        MyAssetManager.dispose();
        super.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return sb;
    }

    public Player getPlayer() {
        return player;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float s) {
        score += s;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void defaultStats() {
        score = GameConstants.MIN_TEMP;
        level = 0;
        savePrefs();
    }

    public void setDefaultScore() {
        score = GameConstants.MIN_TEMP;
    }
    
    public void savePrefs() {
        prefs.putString("score", String.valueOf(score));
        prefs.putInteger("level", level);
        prefs.flush();
    }
}
