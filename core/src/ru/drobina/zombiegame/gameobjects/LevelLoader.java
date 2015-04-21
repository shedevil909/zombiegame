package ru.drobina.zombiegame.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import ru.drobina.zombiegame.gameworld.GameRenderer;
import ru.drobina.zombiegame.helpers.GameConstants;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

public class LevelLoader {

    private XmlReader xml;
    private Element root;
    private Array<Element> levels;
    private Array<Array> allLevels = new Array<Array>();

    public void loadLevels() {
        xml = new XmlReader();
        try {
            root = xml.parse(Gdx.files.internal(GameConstants.levels));
            levels = root.getChildrenByName("level");
        } catch (IOException ex) {
            Logger.getLogger(GameRenderer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        for (int i = 0; i <= GameConstants.LEVELS; i++) {
            allLevels.add(new Array<ObjectMap<String, String>>());
        }

        try {
            Iterator<Element> levelIt = levels.iterator();
            while (levelIt.hasNext()) {
                Element l = levelIt.next();
                int id = l.getIntAttribute("id");
                ObjectMap<String, String> map = new ObjectMap<String, String>();
                for (int c = 0; c < l.getChild(0).getChildCount(); c++) {
                    map.put(l.getChild(0).getChild(c).getName(), l.getChild(0).getChild(c).getText());
                }
                allLevels.get(id).add(map);
            }
        } catch (Exception e) {
            Gdx.app.log("something", "went wrong 1");
        }
    }

    public Array<BodyCell> loadLevel(int id) {

        Array<BodyCell> cells = null;
        try {
            Array<ObjectMap<String, String>> maps = new Array<ObjectMap<String, String>>();
            maps.addAll(allLevels.get(id));
            
            cells = new Array<BodyCell>();
            
            Iterator<ObjectMap<String, String>> c = maps.iterator();
            while (c.hasNext()) {
                ObjectMap<String, String> cell = c.next();
                
                BodyCell newCell;
                
                String type = cell.get("type");
                float scrollSpeed = Float.valueOf(cell.get("scrollSpeed"));
                float spawnSpeed = Float.valueOf(cell.get("spawnSpeed"));
                float score = Float.valueOf(cell.get("score"));
                int quantity = Integer.valueOf(cell.get("quantity"));
                
                if (quantity > 0) {
                    for (int q = 0; q < quantity; q++) {
                        newCell = new BodyCell(MathUtils.random(736), 554, 64, 64,
                                type, score, scrollSpeed, spawnSpeed, quantity);
                        cells.add(newCell);
                    }
                } else {
                    newCell = new BodyCell(MathUtils.random(736), 554, 64, 64, type,
                            score, scrollSpeed, spawnSpeed, quantity);
                    cells.add(newCell);
                }
            }
        } catch (NumberFormatException numberFormatException) {
            Gdx.app.log("something", "went wrong");
        }
        return cells;
    }
}
