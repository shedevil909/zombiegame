package ru.drobina.zombiegame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.drobina.zombiegame.helpers.GameConstants;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    private Circle boundingCircle;
    private static float SPEED = 400;

    private boolean toLeft;
    private boolean toRight;
    private boolean touched;
    private float distance;
    private Vector2 lastTouch;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        boundingCircle = new Circle();
    }

    public void update(float delta) {

        move(delta);

        boundingCircle.set(position.x, position.y, 30f);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void resetGravity() {
        acceleration.setZero();
        velocity.setZero();
    }

    public void setToLeft(boolean left) {
        if (toRight && left) {
            toRight = false;
        }
        toLeft = left;
    }

    public void setToRight(boolean right) {
        if (toLeft && right) {
            toLeft = false;
        }
        toRight = right;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public boolean isTouched() {
        return touched;
    }

    private void move(float delta) {
        if (toLeft) {
            acceleration.x += 200f;
            velocity.add(acceleration.cpy().scl(delta));
            position.sub(velocity.cpy().scl(delta));
        }
        if (toRight) {
            acceleration.x += 200f;
            velocity.add(acceleration.cpy().scl(delta));
            position.add(velocity.cpy().scl(delta));
        }
        if (velocity.x > SPEED) {
            velocity.x = SPEED;
        }
        if (position.x > GameConstants.GAME_WIDTH - width) {
            position.x = GameConstants.GAME_WIDTH - width;
        }
        if (position.x < 0) {
            position.x = 0;
        }
    }
    
    public void dragged(int x) {
        Gdx.app.log("player", "dragged");
        /*while(position.x != x) {
        if(position.x > x) {
        toLeft = true;
        toRight = false;
        }
        if(position.x < x) {
        toRight = true;
        toLeft = false;
        }
        if(position.x == x) {
        toLeft = false;
        toRight = false;
        }
        }*/
    }
}
