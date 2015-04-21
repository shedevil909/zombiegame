package ru.drobina.zombiegame.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import ru.drobina.zombiegame.ZombieGame;
import ru.drobina.zombiegame.helpers.GameConstants;
import java.util.Iterator;

public class LevelLogic {

    private ZombieGame game;
    private Player player;
    private LevelLoader levelLoader;
    private Array<BodyCell> prototypes;
    private Array<BodyCell> spawnArray;

    private boolean levelUp;
    private int lvl;
    private boolean win;
    private float currScore;

    public LevelLogic(ZombieGame game) {
        this.game = game;
        player = game.getPlayer();
        levelLoader = new LevelLoader();
        levelLoader.loadLevels();
        currScore = GameConstants.MIN_TEMP;
        lvl = game.getLevel();
        levelUp = false;
        spawnArray = new Array<BodyCell>();

        initSpawn();
    }

    public void update(float delta) {

        long time = TimeUtils.millis();

        Iterator<BodyCell> it = prototypes.iterator();
        while (it.hasNext()) {
            BodyCell c = it.next();
            long timeToSpawn = TimeUtils.timeSinceMillis(c.getLastSpawnTime());

            if (timeToSpawn > c.getSpawnSpeed() * 1000) {
                c.setLastSpawnTime(time);
                spawn(c);
            }
        }

        it = spawnArray.iterator();
        while (it.hasNext()) {
            BodyCell cell = it.next();
            cell.update(delta);

            if (cell.isScrolledDown) {
                it.remove();
            }

            if (cell.collides(player)) {
                game.setScore(cell.getScore());
                game.setLevel(lvl);
                cell.playSound();
                it.remove();
                checkScore();
            }
        }
    }

    private void spawn(BodyCell c) {
        BodyCell newCell = new BodyCell(MathUtils.random(736), 554,
                c.getW(), c.getH(), c.getType(), c.getScore(),
                c.getScrollSpeed(), c.getSpawnSpeed(), c.getQuantity());
        newCell.setLastSpawnTime(TimeUtils.millis());
        spawnArray.add(newCell);
    }

    private void initSpawn() {
        prototypes = levelLoader.loadLevel(lvl);
        Iterator<BodyCell> pt = prototypes.iterator();
        while (pt.hasNext()) {
            BodyCell pCell = pt.next();
            pCell.setLastSpawnTime(TimeUtils.millis());
            BodyCell newCell = new BodyCell(pCell);
            newCell.position.x = MathUtils.random(736);
            spawnArray.add(newCell);
        }
    }

    public void checkScore() {
        float tempScore = game.getScore();
        if (tempScore >= currScore + GameConstants.LVL_INC) {
            currScore = tempScore;
            lvl++;
            levelUp = true;
            if (lvl < 38) {
                prototypes = levelLoader.loadLevel(lvl);
            }

            if (lvl == 38) {
                win = true;
                game.defaultStats();
            }

            levelUp = false;
        }
        if (tempScore < 36.6) {
            game.setDefaultScore();
        }
    }

    public int getLvl() {
        return lvl;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLevelUp() {
        return levelUp;
    }

    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }

    public Array<BodyCell> getSpawnArray() {
        return spawnArray;
    }

    public float getScore() {
        return game.getScore();
    }
}
