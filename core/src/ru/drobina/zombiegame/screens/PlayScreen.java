package ru.drobina.zombiegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.gameobjects.LevelLogic;
import ru.drobina.zombiegame.gameobjects.Player;
import ru.drobina.zombiegame.gameworld.GameRenderer;
import ru.drobina.zombiegame.gameworld.GameState;
import ru.drobina.zombiegame.helpers.GameConstants;
import ru.drobina.zombiegame.helpers.HUDstage;
import ru.drobina.zombiegame.helpers.InputHandler;

public class PlayScreen implements Screen {

    private ZombieGame game;
    private SpriteBatch sb;
    private OrthographicCamera camera;
    private FitViewport view;
    private HUDstage hudStage;
    private Stage playStage;
    private InputMultiplexer inputMulti;
    private LevelLogic logic;
    private Player player;
    private int lvl;
    private boolean win;
    private float score;
    private GameState state;
    private float runTime;
    private GameRenderer renderer;

    public PlayScreen(ZombieGame game) {
        this.game = game;
        sb = game.getSpriteBatch();
        camera = new OrthographicCamera(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        camera.setToOrtho(false, score, score);
        view = new FitViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, camera);
        hudStage = new HUDstage(game);
        playStage = new Stage(view);

        player = game.getPlayer();
        logic = new LevelLogic(game);

        inputMulti = new InputMultiplexer();
        inputMulti.addProcessor(playStage);
        inputMulti.addProcessor(hudStage);
        inputMulti.addProcessor(new InputHandler(player));
        Gdx.input.setInputProcessor(inputMulti);

        runTime = 0;
        renderer = new GameRenderer(game, logic.getSpawnArray());

    }

    @Override
    public void render(float delta) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        state = game.getState();

        if (state == GameState.RUNNING) {
            logic.update(delta);
            score = logic.getScore();
            lvl = logic.getLvl();
            win = logic.isWin();
            if (lvl >= 1) {
                setLvlTextButton(lvl);
            }

            if (win) {
                game.setScreen(new WinScreen(game));
                win = false;
            }
            setTempScoreTextButton(score);
        }

        if (state == GameState.PAUSE) {

        }

        runTime += delta;
        renderer.render(runTime);
        playStage.act();
        playStage.draw();
        hudStage.act(delta);
        hudStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        hudStage.getViewport().update(width, height, true);
        playStage.getViewport().update(width, height, true);
        renderer.resize(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        game.savePrefs();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        game.savePrefs();
        hudStage.dispose();
        playStage.dispose();
    }

    public void setTempScoreTextButton(float aTemp) {
        String temp = String.valueOf(aTemp);

        if (temp.length() < 6) {
            temp = temp.concat("000");
        }
        char[] output = new char[6];
        temp.getChars(0, 6, output, 0);
        hudStage.getTemp().setText("Your t° is " + new String(output));
    }

    public void setLvlTextButton(int lvl) {
        hudStage.getLvl().setText("Level " + lvl);
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
