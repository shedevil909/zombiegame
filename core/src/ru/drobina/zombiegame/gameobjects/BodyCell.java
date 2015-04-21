package ru.drobina.zombiegame.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.TimeUtils;
import ru.drobina.zombiegame.gameworld.Virus;
import ru.drobina.zombiegame.helpers.MyAssetManager;

public class BodyCell extends Scrollable {

    private Circle boundingCircle;
    private String type;
    private float scrollSpeed;
    private float spawnSpeed;
    private float score;
    private int quantity;
    private long lastSpawnTime;
    private long spawnTime;

    public BodyCell(float x, float y, int w, int h, String type,
            float score, float scrollSpeed, float spawnSpeed, int quantity) {
        super(x, y, w, h, scrollSpeed);
        this.type = type;
        this.score = score;
        this.scrollSpeed = scrollSpeed;
        this.spawnSpeed = spawnSpeed;
        this.quantity = quantity;
    }

    public BodyCell(BodyCell o) {
        super(o.getX(), o.getY(), o.getW(), o.getH(), o.scrollSpeed);
        type = o.type;
        score = o.score;
        scrollSpeed = o.scrollSpeed;
        spawnSpeed = o.spawnSpeed;
        lastSpawnTime = o.lastSpawnTime;
        quantity = o.quantity;
    }

    {
        this.boundingCircle = new Circle();
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        calcSpawnTime();
        boundingCircle.set(position.x, position.y, 20f);
    }

    public boolean collides(Player player) {
        if (position.y < 74) {
            return boundingCircle.overlaps(player.getBoundingCircle());
        }
        return false;
    }

    public void setLastSpawnTime(long lastSpawnTime) {
        this.lastSpawnTime = lastSpawnTime;
    }

    public long getLastSpawnTime() {
        return lastSpawnTime;
    }

    public void calcSpawnTime() {
        spawnTime = TimeUtils.timeSinceMillis(lastSpawnTime);
        if (spawnTime > getSpawnSpeed() * 1000) {
        }
    }

    public String getType() {
        return type;
    }

    public float getScrollSpeed() {
        return scrollSpeed;
    }

    public float getSpawnSpeed() {
        return spawnSpeed;
    }

    public float getScore() {
        return score;
    }

    public int getQuantity() {
        return quantity;
    }

    public void playSound() {
        switch (Virus.valueOf(type).ordinal()) {
            case 0:
                MyAssetManager.redSound.play();
                break;
            case 1:
                MyAssetManager.whiteSound.play();
                break;
            case 2:
                MyAssetManager.cureSound.play();
                break;
            case 3:
                MyAssetManager.redZombiSound.play();
                break;
            case 4:
                MyAssetManager.fluSound.play();
                break;
            case 5:
                MyAssetManager.birdfluSound.play();
                break;
            case 6:
                MyAssetManager.ebolaSound.play();
                break;
            case 7:
                MyAssetManager.hivSound.play();
                break;
            case 8:
                MyAssetManager.fagSound.play();
                break;
            case 9:
                MyAssetManager.cureSound.play();
                break;
        }
    }
}
