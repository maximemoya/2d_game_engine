package java_folder.game_engine;

import java_folder.Main;
import java_folder.game_engine.entity.Entity;
import java_folder.game_engine.entity.Player;
import java_folder.game_engine.map.MapManager;
import java_folder.game_engine.map.TileSetManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS :
    static final int ORIGINAL_TILE_SIZE = 16;
    public static int SCALE = Main.SCALE;

    public static int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static int SCREEN_COLUMN = 19;
    public static int SCREEN_ROW = 13;
    public static int GAME_WIDTH = TILE_SIZE * SCREEN_COLUMN;
    public static int GAME_HEIGHT = TILE_SIZE * SCREEN_ROW;

    // FPS
    final int FPS = 70;
    final double intervalFPS = 1_000_000_000d / FPS;
    private int fps_logic = 0;
    private int fps_graphic = 0;

    //    Thread gameThreadLogic = new Thread(this::run_logic);
    Thread gameThreadGraphic = new Thread(this::run_graphic);
    boolean gameIsRunning = true;

    // DRAW :
    Graphics2D g2;
    public static int[] posPointUpLeftCorner = new int[2];

    // TILESET :
    TileSetManager tileSetManager = new TileSetManager();
    public static MapManager mapManager;

    // ENTITIES :
    Player player = new Player(this, GlobalPanel.keyHandler, GlobalPanel.mouseHandler);
    Entity[] entitiesArray = new Entity[16];

    // -C-O-N-S-T-R-U-C-T-O-R- :
    public GamePanel() {

        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(GlobalPanel.keyHandler);
        this.addMouseListener(GlobalPanel.mouseHandler);
        this.setFocusable(true);

//        this.setFont(new Font ("Courier New", Font.BOLD, GAME_WIDTH/60));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("Font/dialog_font.ttf");
            assert stream != null;
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(18f);
            ge.registerFont(font);
            this.setFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // TILESET :
//        tileSetManager.addTileSet16x16x16("Tiles/myTileSet16x16.png");
//        int[][] dataIdMap = {
//                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
//                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//                {3,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                {3,0,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
//                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
//                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
//                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,3},
//                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
//                {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
//                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
//        };
        mapManager = new MapManager();
        mapManager.loadMap("Map/newMap.mmap");

        // ENTITIES :
        entitiesArray[0] = player;
        for (Entity entity : entitiesArray) {
            if (entity != null) {
                entity.setDefaultValues();
            }
        }

        // THREAD LOGIC AND GRAPHIC :
//        gameThreadLogic.start();
        gameThreadGraphic.start();

    }

    // UPDATE : LOGIC
    public void update() {
        if (GlobalPanel.mouseHandler.pressedButtonArray[MouseHandler.LEFT_BUTTON]) {
            int idX = (GlobalPanel.mouseHandler.positionPressedXArray[MouseHandler.LEFT_BUTTON] - posPointUpLeftCorner[0]) / TILE_SIZE;
            int idY = (GlobalPanel.mouseHandler.positionPressedYArray[MouseHandler.LEFT_BUTTON] - posPointUpLeftCorner[1]) / TILE_SIZE;
            if (idX == player.actualPosSlotX && idY == player.actualPosSlotY) {
                player.circleTiming = 15;
            } else {
                try {
                    mapManager.dataIDMap[idY][idX] = 4;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (GlobalPanel.mouseHandler.pressedButtonArray[MouseHandler.RIGHT_BUTTON]) {
            int idX = (GlobalPanel.mouseHandler.positionPressedXArray[MouseHandler.RIGHT_BUTTON] - posPointUpLeftCorner[0]) / TILE_SIZE;
            int idY = (GlobalPanel.mouseHandler.positionPressedYArray[MouseHandler.RIGHT_BUTTON] - posPointUpLeftCorner[1]) / TILE_SIZE;
            try {
                mapManager.dataIDMap[idY][idX] = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void run_logic() {

        System.out.println("start : gameThreadLogic(run_logic)");
        long timeNow = System.nanoTime();
        double nextInterval = timeNow + intervalFPS;
        int frameRate = 0;
        double nextSecond = timeNow + 1_000_000_000;

        while (gameIsRunning) {
            timeNow = System.nanoTime();

            if (timeNow > nextSecond) {
                nextSecond = timeNow + 1_000_000_000;
                // to show frameRate :
//                System.out.println("LOGIC FPS : " + frameRate);
                fps_logic = frameRate;
                frameRate = 0;
            }

            if (timeNow > nextInterval) {
                // FPS :
                nextInterval = timeNow + intervalFPS;
                // to show frameRate :
                frameRate++;
                // UPDATE LOGIC :
                update();
            }
        }
    }

    // UPDATE : GRAPHIC :
    public void run_graphic() {

        System.out.println("start : gameThreadGraphic(run_graphic)");
        long timeNow = System.nanoTime();
        double nextInterval = timeNow + intervalFPS;
        int frameRate = 0;
        double nextSecond = timeNow + 1_000_000_000;

        while (gameIsRunning) {

            timeNow = System.nanoTime();
            if (timeNow > nextSecond) {
                nextSecond = timeNow + 1_000_000_000;
                // to show frameRate :
//                System.out.println("GRAPHIC FPS : " + frameRate);
                fps_graphic = frameRate;
                frameRate = 0;
            }

            if (timeNow > nextInterval) {
                // FPS :
                nextInterval = timeNow + intervalFPS;
                // to show frameRate :
                frameRate++;
                // UPDATE GRAPHIC :
                update();
                this.repaint();

            }

            Thread.yield();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2 = (Graphics2D) g;

//        tileSetManager.testDrawTileSet(g2);

        mapManager.draw(g2);

        for (Entity entity : entitiesArray) {
            if (entity != null) {
                entity.update();
                entity.draw(g2);
            }
        }

        g2.setColor(Color.WHITE);
        g2.drawString("Graphic_FPS : " + fps_graphic, GAME_WIDTH - (GAME_WIDTH / 6), GAME_HEIGHT / 25);
//        g2.drawString("Logic_FPS : " + fps_logic, GAME_WIDTH - (GAME_WIDTH /7), GAME_HEIGHT /15);
        g2.drawString("posPointUpLeftCorner : " + posPointUpLeftCorner[0] + " | " + posPointUpLeftCorner[1],
                0, 125);
        g2.drawString("MOUSE Pressed : " +
                        Arrays.toString(GlobalPanel.mouseHandler.pressedButtonArray)
                + " | X : " + Arrays.toString(GlobalPanel.mouseHandler.positionPressedXArray)
                + " | Y : " + Arrays.toString(GlobalPanel.mouseHandler.positionPressedYArray),
                0, 200);

        g2.dispose();

    }
}
