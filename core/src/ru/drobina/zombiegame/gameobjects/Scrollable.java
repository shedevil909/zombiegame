package ru.drobina.zombiegame.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledDown;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);
        this.width = width;
        this.height = height;
        isScrolledDown = false;
    }

    public void update(float delta) {
        position.sub(velocity.cpy().scl(delta));
        if (position.y < -height) {
            isScrolledDown = true;
        }
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }

    public boolean isScrolledDown() {
        return isScrolledDown;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
