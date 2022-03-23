package java_folder.game_engine.map;

import java_folder.game_engine.GamePanel;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class MapManager implements Serializable {

    public int[][] dataIDMap;
    TileSetManager tileSetManager;

    public MapManager() {}

    public MapManager(int[][] dataIDMap, TileSetManager tilesetManager) {

        this.dataIDMap = dataIDMap;
        this.tileSetManager = tilesetManager;

    }

    int mapIndexLeftLimitX;
    int mapIndexRightLimitX;
    int mapIndexUpLimitY;
    int mapIndexDownLimitY;

    public void saveMap(String mapName){

        try {
            ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream("resources/Map/" + mapName)) ;
            oos.writeObject(this) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filepath){

        try {
            ObjectInputStream ois =  new ObjectInputStream(getClass().getClassLoader().getResourceAsStream(filepath));
            MapManager load = (MapManager) ois.readObject() ;
            dataIDMap = new int[load.dataIDMap.length][load.dataIDMap[0].length];
            for (int i = 0; i < dataIDMap.length; i++) {
                System.arraycopy(load.dataIDMap[i], 0, dataIDMap[i], 0, dataIDMap[i].length);
            }
            tileSetManager = load.tileSetManager;
            tileSetManager.addTileSet16x16x16(tileSetManager.filepathTileSet);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g2) {

        mapIndexLeftLimitX = -GamePanel.posPointUpLeftCorner[0] / GamePanel.TILE_SIZE;
        mapIndexRightLimitX = 1 + (-GamePanel.posPointUpLeftCorner[0] + GamePanel.GAME_WIDTH) / GamePanel.TILE_SIZE;
        mapIndexUpLimitY = -GamePanel.posPointUpLeftCorner[1] / GamePanel.TILE_SIZE;
        mapIndexDownLimitY = 1 + (-GamePanel.posPointUpLeftCorner[1] + GamePanel.GAME_HEIGHT) / GamePanel.TILE_SIZE;

        for (int i = mapIndexUpLimitY; i < mapIndexDownLimitY; i++) {
            for (int j = mapIndexLeftLimitX; j < mapIndexRightLimitX; j++) {
                if(dataIDMap.length > i && i > -1 && dataIDMap[i].length > j && j > -1){
                    g2.drawImage(
                            tileSetManager.getImageByID(dataIDMap[i][j]),
                            GamePanel.posPointUpLeftCorner[0] + j * GamePanel.TILE_SIZE,
                            GamePanel.posPointUpLeftCorner[1] + i * GamePanel.TILE_SIZE,
                            GamePanel.TILE_SIZE,
                            GamePanel.TILE_SIZE,
                            null
                    );
                }
            }
        }
        g2.setColor(Color.WHITE);
        g2.drawString("mapIndexLeftLimitX : " + mapIndexLeftLimitX,0,25);
        g2.drawString("mapIndexRightLimitX : " + mapIndexRightLimitX,0,50);
        g2.drawString("mapIndexUpLimitY : " + mapIndexUpLimitY,0,75);
        g2.drawString("mapIndexDownLimitY : " + mapIndexDownLimitY,0,100);

    }

}
