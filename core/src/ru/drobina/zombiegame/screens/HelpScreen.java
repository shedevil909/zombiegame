package ru.drobina.zombiegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.helpers.GameConstants;
import ru.drobina.zombiegame.helpers.MyAssetManager;

public class HelpScreen implements Screen {

    private ZombieGame game;
    private SpriteBatch sb;
    private OrthographicCamera camera;
    private Stage stage;
    private TextButton backButton;
    private Table helpTable, paneTable;
    private Label.LabelStyle lblStyle, lblText;
    private TextButton.TextButtonStyle buttonStyle;
    private Image red, white, cure, redZombi, flu, birdflu, ebola, hiv, fag;
    private ScrollPane pane;
    private String text, redText, whiteText, cureText, redZombiText, fluText, birdfluText, ebolaText, hivText, fagText;
    private Label descr, redLbl, whiteLbl, cureLbl, redZombiLbl, fluLbl, birdfluLbl, ebolaLbl, hivLbl, fagLbl;
    private String increaseText, decreaseText;
    private Label increaseLbl, decreaseLbl;

    public HelpScreen(final ZombieGame game) {
        this.game = game;
        sb = game.getSpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        stage = new Stage(new FitViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, camera), sb);

        initAssets();
        initTables();
        initText();
        createHelpTable();
    }

    private void initAssets() {
        red = new Image(new TextureRegionDrawable(MyAssetManager.red));
        white = new Image(new TextureRegionDrawable(MyAssetManager.white));
        cure = new Image(new TextureRegionDrawable(MyAssetManager.cure));
        redZombi = new Image(new TextureRegionDrawable(MyAssetManager.redZombi));
        flu = new Image(new TextureRegionDrawable(MyAssetManager.flu));
        birdflu = new Image(new TextureRegionDrawable(MyAssetManager.birdflu));
        ebola = new Image(new TextureRegionDrawable(MyAssetManager.ebola));
        hiv = new Image(new TextureRegionDrawable(MyAssetManager.hiv));
        fag = new Image(new TextureRegionDrawable(MyAssetManager.fag));
    }

    private void initTables() {
        helpTable = new Table(MyAssetManager.skin);
        helpTable.padTop(25);

        paneTable = new Table(MyAssetManager.skin);
        paneTable.right();
        paneTable.pad(20);
        paneTable.setFillParent(true);
    }

    private void initText() {
        //styles
        lblStyle = new Label.LabelStyle();
        lblStyle.font = MyAssetManager.skin.getFont("default-font");
        lblStyle.fontColor = MyAssetManager.skin.getColor("white");
        lblText = new Label.LabelStyle();
        lblText.font = new BitmapFont();
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = MyAssetManager.skin.getFont("default-font");
        buttonStyle.up = MyAssetManager.skin.getDrawable("longbutton");
        backButton = new TextButton("Back", buttonStyle);
        backButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        //text, labels             
        text = "Catch all the bloodcells to make your carrier become a zombie. Avoid viruses as they cool down your temperature";
        redText = "Red blood cell";
        whiteText = "White blood cell";
        cureText = "Cure";
        redZombiText = "Infected red blood cell";
        fluText = "Blood cell absorbed by the Flu virus";
        birdfluText = "Bird Flu virus is also contaminous";
        ebolaText = "Famous Ebola virus spreads very fast";
        hivText = "HIV isn't good for you";
        fagText = "Phage is every virus' most cruel enemy";
        increaseText = "Increase";
        decreaseText = "Decrease";

        redLbl = new Label(redText, lblText);
        redLbl.setWrap(true);
        whiteLbl = new Label(whiteText, lblText);
        whiteLbl.setWrap(true);
        cureLbl = new Label(cureText, lblText);
        cureLbl.setWrap(true);
        redZombiLbl = new Label(redZombiText, lblText);
        redZombiLbl.setWrap(true);
        fluLbl = new Label(fluText, lblText);
        fluLbl.setWrap(true);
        birdfluLbl = new Label(birdfluText, lblText);
        birdfluLbl.setWrap(true);
        ebolaLbl = new Label(ebolaText, lblText);
        ebolaLbl.setWrap(true);
        hivLbl = new Label(hivText, lblText);
        hivLbl.setWrap(true);
        fagLbl = new Label(fagText, lblText);
        fagLbl.setWrap(true);
        descr = new Label(text, lblStyle);
        descr.setWrap(true);
        descr.setAlignment(Align.center);
        increaseLbl = new Label(increaseText, lblStyle);
        increaseLbl.setAlignment(Align.center);
        decreaseLbl = new Label(decreaseText, lblStyle);
        decreaseLbl.setAlignment(Align.center);
    }

    private void createHelpTable() {
        helpTable.add(descr).width(500).padBottom(50).colspan(4);
        helpTable.row();
        helpTable.add(increaseLbl).width(250).padBottom(20).colspan(2);
        helpTable.add(decreaseLbl).width(250).padBottom(20).colspan(2);
        helpTable.row();

        helpTable.columnDefaults(0).size(64, 64).center().padBottom(10).padRight(10);
        helpTable.columnDefaults(1).size(186, 64).center().padBottom(10).padRight(10);
        helpTable.columnDefaults(2).size(64, 64).left().padBottom(10).padRight(10);
        helpTable.columnDefaults(3).size(186, 64).left().padBottom(10);

        helpTable.add(redZombi);
        helpTable.add(redZombiLbl);
        helpTable.add(cure);
        helpTable.add(cureLbl);
        helpTable.row();

        helpTable.add(red);
        helpTable.add(redLbl);
        helpTable.add(flu);
        helpTable.add(fluLbl);
        helpTable.row();

        helpTable.add(white);
        helpTable.add(whiteLbl);
        helpTable.add(birdflu);
        helpTable.add(birdfluLbl);
        helpTable.row();

        helpTable.add(new Label(" ", lblText));
        helpTable.add(new Label(" ", lblText));

        helpTable.add(ebola);
        helpTable.add(ebolaLbl);
        helpTable.row();

        helpTable.add(new Label(" ", lblText));
        helpTable.add(new Label(" ", lblText));
        helpTable.add(hiv);
        helpTable.add(hivLbl);
        helpTable.row();

        helpTable.add(new Label(" ", lblText));
        helpTable.add(new Label(" ", lblText));
        helpTable.add(fag);
        helpTable.add(fagLbl);
        helpTable.row();

        pane = new ScrollPane(helpTable);
        paneTable.add(pane);
        paneTable.add(backButton).width(120).height(50).bottom().padLeft(20);

        stage.addActor(paneTable);
        Gdx.input.setInputProcessor(stage);
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
