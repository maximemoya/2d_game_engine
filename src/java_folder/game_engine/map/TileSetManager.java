package java_folder.game_engine.map;

import java_folder.game_engine.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class TileSetManager implements Serializable {

    transient BufferedImage tileSet;

    String filepathTileSet = "";
    int sizeX = 0;
    int sizeY = 0;
    int column = 0;
    int line = 0;

    public TileSetManager() {
    }

    public void addTileSet16x16x16(String filepath) {

        try {
            tileSet = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filepath)));
            filepathTileSet = filepath;
            sizeX = tileSet.getWidth();
            sizeY = tileSet.getHeight();
            column = 16;
            line = 16;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImageByID(int id){

        int width = (sizeX/line);
        int height = (sizeY/column);
        int xLine = (id % column) * width;
        int yColumn = (id / line) * height;

        return tileSet.getSubimage(
                xLine,
                yColumn,
                width,
                height
        );
    }

    public void testDrawTileSet(Graphics2D g2) {
        if (tileSet != null) {
            g2.drawImage(tileSet,
                    GamePanel.posPointUpLeftCorner[0],
                    GamePanel.posPointUpLeftCorner[1],
                    GamePanel.posPointUpLeftCorner[0] + sizeX * GamePanel.SCALE,
                    GamePanel.posPointUpLeftCorner[1] + sizeY * GamePanel.SCALE,
                    0,
                    0,
                    sizeX,
                    sizeY,
                    null);
        }
    }

}
