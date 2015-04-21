package ru.drobina.zombiegame.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import ru.drobina.zombiegame.gameobjects.Player;

public class InputHandler implements InputProcessor {

    private OrthographicCamera camera;
    private Player player;
    private Vector3 touchPos;

    // TO DO android touch
        /*
     if (Gdx.input.isTouched()) {
     Vector3 touchPos = new Vector3();
     touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
     camera.unproject(touchPos);
     player.addAction(Actions.moveTo(touchPos.x, 0, 1f, Interpolation.bounceOut));
            
     }
     */
    public InputHandler(Player p) {
        camera = new OrthographicCamera(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
        camera.setToOrtho(false);
        player = p;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                player.setToLeft(true);
                break;
            case Keys.RIGHT:
                player.setToRight(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        player.resetGravity();
        switch (keycode) {
            case Keys.LEFT:
                player.setToLeft(false);
                break;
            case Keys.RIGHT:
                player.setToRight(false);
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //camera.unproject(touchPos.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer),  0));
        touchPos = new Vector3();
        touchPos.set(screenX, Gdx.graphics.getHeight()-player.getHeight()/2-screenY, 0);

        System.out.println(player.getBoundingCircle().x+" "+ player.getBoundingCircle().y);
        System.out.println(touchPos.x+" "+ touchPos.y);
        
        if(player.getBoundingCircle().contains(touchPos.x, touchPos.y)) {
            player.setTouched(true);
            return true;
        }else{
            player.setTouched(false);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        player.resetGravity();
        player.setToLeft(false);
        player.setToRight(false);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //camera.unproject(touchPos.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer), 0));
        Vector3 newTouchPos = new Vector3(screenX, Gdx.graphics.getHeight()-player.getHeight()/2-screenY, 0);
        
        while(player.isTouched()) {
            if(newTouchPos.x > touchPos.x) {
                keyDown(Keys.RIGHT);
            }
            if(newTouchPos.x < touchPos.x) {
                keyDown(Keys.LEFT);
            }
            Gdx.app.log("player", "touched and dragged");
            //player.dragged(screenX-player.getWidth()/2);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
