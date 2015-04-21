package ru.drobina.zombiegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.gameworld.GameState;
import ru.drobina.zombiegame.helpers.GameConstants;
import ru.drobina.zombiegame.helpers.MyAssetManager;

public class MenuScreen implements Screen {

    private final ZombieGame game;
    private SpriteBatch sb;
    private OrthographicCamera camera;
    private Stage stage;
    private TextButton buttonPlay, buttonExit, buttonHelp;
    private Table tableWelcome;
    private Label title, welcome, lastTemp;
    private LabelStyle lblStyle;
    private TextButtonStyle buttonStyle;
    private String temp;

    public MenuScreen(final ZombieGame game) {
        this.game = game;
        sb = game.getSpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        this.stage = new Stage(new FitViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, camera), sb);

        tableWelcome = new Table();
        lblStyle = new LabelStyle();
        lblStyle.font = MyAssetManager.skin.getFont("default-font");
        lblStyle.fontColor = MyAssetManager.skin.getColor("white");
        title = new Label("Want to be a zombie?", MyAssetManager.skin);

        buttonStyle = new TextButtonStyle();
        buttonStyle.font = MyAssetManager.skin.getFont("default-font");
        buttonStyle.up = MyAssetManager.skin.getDrawable("longbutton");
        buttonExit = new TextButton("Exit", buttonStyle);
        buttonExit.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonPlay = new TextButton("Play", buttonStyle);
        buttonPlay.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
                game.setState(GameState.RUNNING);
                dispose();
            }
        });
        buttonHelp = new TextButton("Help", buttonStyle);
        buttonHelp.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HelpScreen(game));
                dispose();
            }
        });

        temp = String.valueOf(game.getScore());

        if (temp.length() < 6) {
            temp = temp.concat("000");
        }
        char[] output = new char[6];
        temp.getChars(0, 6, output, 0);
        
        welcome = new Label("Welcome, dear!", MyAssetManager.skin);
        lastTemp = new Label("Your temperature was " + new String(output), MyAssetManager.skin);
        lastTemp.setAlignment(Align.center);
    }

    @Override
    public void render(float delta) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(MyAssetManager.background, 0, 0);
        sb.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        tableWelcome.add(title).colspan(3).space(50).row();
        tableWelcome.add(welcome).colspan(3).space(10).row();
        tableWelcome.add(lastTemp).colspan(3).space(10).padBottom(30).row();
        tableWelcome.row();
        tableWelcome.add(buttonHelp).width(100).height(50).space(10);
        tableWelcome.add(buttonPlay).width(100).height(50).space(10);
        tableWelcome.add(buttonExit).width(100).height(50).space(10);
        tableWelcome.setFillParent(true);
        stage.addActor(tableWelcome);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
    }
}
