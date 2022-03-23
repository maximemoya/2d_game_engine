package java_folder.game_engine.entity;

import java_folder.game_engine.GamePanel;
import java_folder.game_engine.KeyHandler;
import java_folder.game_engine.MouseHandler;
import java_folder.game_engine.map.MyTileset16x16Enum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    private GamePanel gp;
    private KeyHandler keyH;
    private MouseHandler mouseH;

    public int circleTiming = 0;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;
        this.isMoving = false;
        loadSpriteSheet(16,16);
        setDefaultValues();
    }

    public void setDefaultValues(){
        super.x = GamePanel.posPointUpLeftCorner[0] + (GamePanel.GAME_WIDTH - GamePanel.TILE_SIZE) / 2;
        super.y = GamePanel.posPointUpLeftCorner[1] + (GamePanel.GAME_HEIGHT - GamePanel.TILE_SIZE) / 2;
        super.sizeX = GamePanel.TILE_SIZE;
        super.sizeY = GamePanel.TILE_SIZE;
        super.speed = GamePanel.TILE_SIZE/6;
        super.direction = DIRECTION_DOWN;
        super.collisionBox.setBounds(x,y,sizeX,sizeY);
    }

    public void loadSpriteSheet(int spriteTileSizeX, int spriteTileSizeY){
        try {
            spritesheet = ImageIO.read(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                            "Player/spritesheet_player_blue.png")));
            this.spriteSheetTileSizeX = spriteTileSizeX;
            this.spriteSheetTileSizeY = spriteTileSizeY;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    int dUpx;
    int dUpy;
    int dLeftx;
    int dLefty;
    int dDownx;
    int dDowny;
    int dRightx;
    int dRighty;
    int dCenterx;
    int dCentery;

    @Override
    public void update() {

        isMoving = keyH.isMoving;
        absolutePosX = x - GamePanel.posPointUpLeftCorner[0];
        absolutePosY = y - GamePanel.posPointUpLeftCorner[1];

        dUpx = (absolutePosX + sizeX/2) / sizeX;
        dUpy = absolutePosY / sizeY;

        dLeftx = absolutePosX / sizeX;
        dLefty = (absolutePosY + sizeY/2) / sizeY;

        dDownx = (absolutePosX + sizeX/2) / sizeX;
        dDowny = (absolutePosY + sizeY) / sizeY;
        super.actualPosSlotX = dDownx;
        super.actualPosSlotY = dDowny;
        super.actualIDSlot = GamePanel.mapManager.dataIDMap[dDowny][dDownx];

        dRightx = (absolutePosX + sizeX) / sizeX;
        dRighty = (absolutePosY + sizeY/2) / sizeY;

        dCenterx = (absolutePosX + sizeX/2) / sizeX;
        dCentery = (absolutePosY + sizeY/2) / sizeY;

        if (keyH.moveKeyState[DIRECTION_UP]){
            this.direction = DIRECTION_UP;
            GamePanel.posPointUpLeftCorner[1] += this.speed;

            try{
                if(GamePanel.mapManager.dataIDMap[dUpy][dUpx] == 4){
                    GamePanel.posPointUpLeftCorner[1] -= this.speed;
                }
            }
            catch (Exception ignored){
                GamePanel.posPointUpLeftCorner[1] -= this.speed;
            }
        }
        else if (keyH.moveKeyState[DIRECTION_DOWN]){
            this.direction = DIRECTION_DOWN;
            GamePanel.posPointUpLeftCorner[1] -= this.speed;

            try{
                if(GamePanel.mapManager.dataIDMap[dDowny][dDownx] == 4){
                    GamePanel.posPointUpLeftCorner[1] += this.speed;
                }
            }
            catch (Exception ignored){
                GamePanel.posPointUpLeftCorner[1] += this.speed;
            }
        }

        if (keyH.moveKeyState[DIRECTION_LEFT]){
            this.direction = DIRECTION_LEFT;
            GamePanel.posPointUpLeftCorner[0] += this.speed;

            try{
                if(GamePanel.mapManager.dataIDMap[dLefty][dLeftx] == 4){
                    GamePanel.posPointUpLeftCorner[0] -= this.speed;
                }
            }
            catch (Exception ignored){
                GamePanel.posPointUpLeftCorner[0] -= this.speed;
            }
        }
        else if (keyH.moveKeyState[DIRECTION_RIGHT]){
            this.direction = DIRECTION_RIGHT;
            GamePanel.posPointUpLeftCorner[0] -= this.speed;

            try{
                if(GamePanel.mapManager.dataIDMap[dRighty][dRightx] == 4){
                    GamePanel.posPointUpLeftCorner[0] += this.speed;
                }
            }
            catch (Exception ignored){
                GamePanel.posPointUpLeftCorner[0] += this.speed;
            }
        }

        if (mouseH.pressedButtonArray[MouseHandler.LEFT_BUTTON]){
            int posX = mouseH.positionPressedXArray[MouseHandler.LEFT_BUTTON];
            int posY = mouseH.positionPressedYArray[MouseHandler.LEFT_BUTTON];
            System.out.println("POSx : " + posX);
            System.out.println("POSy : " + posY);
            if(collisionBox.contains(posX, posY)){
                circleTiming = 20;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        setImage();
        g2.drawImage(spritesheet,x,y,x + sizeX,y + sizeY, spriteSheetX, spriteSheetY, spriteSheetX + 16, spriteSheetY + 16, null);
        g2.setColor(Color.WHITE);
        g2.drawString("Player : " + absolutePosX + "|" + absolutePosY,50,150);
        try{

            g2.drawString("SlotID : " +
                    GamePanel.mapManager.dataIDMap[dDowny][dDownx] + " => " +
                    MyTileset16x16Enum.values()[GamePanel.mapManager.dataIDMap[dDowny][dDownx]],100,175);
        }
        catch (Exception ignored){}

        if (circleTiming > 0){

            if (circleTiming > 10){
                g2.setColor(new Color(255,255,255,50));
                g2.fillRect(x-5,y-5,sizeX+10,sizeY+10);
                g2.setColor(Color.RED);
                g2.drawRect(x-5,y-5,sizeX+10,sizeY+10);
            }
            else if(circleTiming > 5){
                g2.setColor(new Color(255,255,255,50));
                g2.fillRect(x,y,sizeX,sizeY);
                g2.setColor(Color.RED);
                g2.drawRect(x,y,sizeX,sizeY);
            }
            else {
                g2.setColor(new Color(255,255,255,50));
                g2.fillRect(x+10,y+10,sizeX-20,sizeY-20);
                g2.setColor(Color.RED);
                g2.drawRect(x+10,y+10,sizeX-20,sizeY-20);
            }
            circleTiming--;
        }
        else {
            g2.setColor(new Color(255,255,255,50));
            g2.fillRect(x,y,sizeX,sizeY);
            g2.setColor(Color.GREEN);
            g2.drawRect(x,y,sizeX,sizeY);
        }
    }
}
