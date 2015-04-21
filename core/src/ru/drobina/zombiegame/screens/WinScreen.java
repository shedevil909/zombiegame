package ru.drobina.zombiegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.helpers.GameConstants;
import ru.drobina.zombiegame.helpers.MyAssetManager;

public class WinScreen implements Screen {
    
    private final ZombieGame game;
    private SpriteBatch sb;
    private OrthographicCamera camera;
    private Stage stage;
    
    private BitmapFont font;
    
    private Image psyduck;
    private Table table;
    private Label label;
    private String text = "Gongratulations! You're now a zombie.";
    
    private TextButton againBtn, quitBtn;
    private final TextButton.TextButtonStyle buttonStyle;
    
    public WinScreen (final ZombieGame game) {
        this.game = game;
        sb = game.getSpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        stage = new Stage(new FitViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, camera), sb);
        font = MyAssetManager.font;
        table = new Table(MyAssetManager.skin);
        label = new Label(text, MyAssetManager.skin);
        label.setAlignment(Align.center);

        psyduck = new Image();
        psyduck.setDrawable(new TextureRegionDrawable(new TextureRegion(MyAssetManager.psyduck)));
        psyduck.setScaling(Scaling.fit);
        
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = MyAssetManager.skin.getFont("default-font");
        buttonStyle.up = MyAssetManager.skin.getDrawable("button");
        
        againBtn = new TextButton("Play again", buttonStyle);
        againBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
            
        });
        
        quitBtn = new TextButton("Quit", buttonStyle);
        quitBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.setFillParent(true);
        table.add(psyduck).width(400).height(250).align(Align.center).colspan(2).pad(25,0,10,0).row();
        table.add(label).align(Align.center).colspan(2).pad(10,0,10,0).row();
        table.add(againBtn).pad(10,0,25,0);
        table.add(quitBtn).pad(10,0,25,0).row();
        
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        MyAssetManager.medSound.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        
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
