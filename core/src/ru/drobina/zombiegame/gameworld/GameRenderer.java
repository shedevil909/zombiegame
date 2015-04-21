package ru.drobina.zombiegame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.gameobjects.Player;
import ru.drobina.zombiegame.gameobjects.BodyCell;
import ru.drobina.zombiegame.helpers.MyAssetManager;
import java.util.HashMap;
import ru.drobina.zombiegame.helpers.GameConstants;

public class GameRenderer {

    private OrthographicCamera camera;
    private SpriteBatch sb;
    private FitViewport viewport;
    
    private Player player;
    private Array<BodyCell> renderArray;

    //Assets
    private Texture bg;
    private Animation playerAnimation, cureAnimation;
    private HashMap<String, TextureRegion> map;

    public GameRenderer(ZombieGame game, Array<BodyCell> renderArray) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb = game.getSpriteBatch();
        viewport = new FitViewport(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT, camera);
        player = game.getPlayer();
        this.renderArray = renderArray;
        map = new HashMap<String, TextureRegion>();
        initAssets();
    }

    private void initAssets() {
        bg = MyAssetManager.background;
        playerAnimation = MyAssetManager.playerAnimation;
        cureAnimation = MyAssetManager.cureAnimation;

        map.put("red", MyAssetManager.red);
        map.put("white", MyAssetManager.white);
        map.put("cure", MyAssetManager.cure);
        map.put("redZombi", MyAssetManager.redZombi);
        map.put("flu", MyAssetManager.flu);
        map.put("birdflu", MyAssetManager.birdflu);
        map.put("ebola", MyAssetManager.ebola);
        map.put("hiv", MyAssetManager.hiv);
        map.put("fag", MyAssetManager.fag);
    }

    public void render(float runTime) {
        
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        sb.begin();
        sb.draw(bg, 0, 0);

        for (BodyCell cell : renderArray) {
            String key = cell.getType();
            if (key.equals("medicine")) {
                sb.draw(map.get("cure"), cell.getX(), cell.getY(), map.get("cure").getRegionWidth(), map.get("cure").getRegionHeight());
            } else if (key.equals("cure")) {
                sb.draw(cureAnimation.getKeyFrame(runTime), cell.getX(), cell.getY(), map.get(key).getRegionWidth(), map.get(key).getRegionHeight());
            } else {
                sb.draw(map.get(key), cell.getX(), cell.getY(), map.get(key).getRegionWidth(), map.get(key).getRegionHeight());
            }
        }
        sb.draw(playerAnimation.getKeyFrame(runTime), player.getX(), player.getY(), player.getWidth(), player.getHeight());
        sb.end();
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
