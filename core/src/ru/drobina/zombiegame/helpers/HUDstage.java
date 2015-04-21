package ru.drobina.zombiegame.helpers;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.gameworld.GameState;
import ru.drobina.zombiegame.screens.MenuScreen;

public class HUDstage extends Stage {

    private TextButton temp, exit, toMenu, lvl, pause;
    private ImageButton resumeButton;
    private TextButtonStyle exitButtonStyle, pauseButtonStyle, toMenuButtonStyle,
            tempButtonStyle, lvlButtonStyle;
    private Table table;
    private ZombieGame game;

    public HUDstage(final ZombieGame game) {
        this.game = game;
        this.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        
        table = new Table(MyAssetManager.skin);
        table.left().top();
        table.pad(10);

        exitButtonStyle = new TextButtonStyle();
        pauseButtonStyle = new TextButtonStyle();
        toMenuButtonStyle = new TextButtonStyle();
        tempButtonStyle = new TextButtonStyle();
        lvlButtonStyle = new TextButtonStyle();

        exitButtonStyle.font = MyAssetManager.skin.getFont("default-font");
        pauseButtonStyle.font = MyAssetManager.skin.getFont("default-font");
        toMenuButtonStyle.font = MyAssetManager.skin.getFont("default-font");
        tempButtonStyle.font = MyAssetManager.skin.getFont("default-font");
        lvlButtonStyle.font = MyAssetManager.skin.getFont("default-font");

        exitButtonStyle.up = MyAssetManager.skin.getDrawable("exit");
        pauseButtonStyle.up = MyAssetManager.skin.getDrawable("longbutton");
        toMenuButtonStyle.up = MyAssetManager.skin.getDrawable("lvls");
        tempButtonStyle.up = MyAssetManager.skin.getDrawable("longbutton");
        lvlButtonStyle.up = MyAssetManager.skin.getDrawable("longbutton");

        temp = new TextButton("Your t° is ", tempButtonStyle);
        lvl = new TextButton("Catch them!", tempButtonStyle);
        exit = new TextButton("", exitButtonStyle);
        exit.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.savePrefs();
                if (Gdx.app.getType().equals(ApplicationType.WebGL)) {
                    game.setScreen(new MenuScreen(game));
                }
                Gdx.app.exit();
            }

        });
        toMenu = new TextButton("", toMenuButtonStyle);
        toMenu.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.savePrefs();
                game.setScreen(new MenuScreen(game));
            }
        });

        pause = new TextButton("Pause", pauseButtonStyle);
        pause.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.savePrefs();
                resumeButton.setVisible(true);
                game.setState(GameState.PAUSE);
            }

        });

        resumeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(MyAssetManager.pause)));
        resumeButton.setVisible(false);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, Align.center);
        resumeButton.getColor().a = 0.8f;
        resumeButton.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                resumeButton.setVisible(false);
                game.setState(GameState.RUNNING);
            }

        });

        table.add(temp).width(200).height(40).left().expandX();
        table.add(lvl).width(200).height(40).left().expandX();
        table.add(pause).width(100).height(40).right().space(10);
        table.add(toMenu).size(40).right().space(10);
        table.add(exit).size(40).right();
        table.setFillParent(true);
        addActor(table);
        addActor(resumeButton);

    }

    public TextButton getTemp() {
        return temp;
    }

    public TextButton getLvl() {
        return lvl;
    }
    
}
