package java_folder.game_engine.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    // POSITION :
    public int x, y;
    public int speed;

    // IMAGE :
    public BufferedImage spritesheet;
    public int sizeX, sizeY;
    public int spriteSheetTileSizeX = 0;
    public int spriteSheetTileSizeY = 0;
    public int spriteSheetX = 0;
    public int spriteSheetY = 0;
    public int actualPosSlotX = 0;
    public int actualPosSlotY = 0;
    public int actualIDSlot = 0;

    public int frameCount = 0;
    final public int frameBetweenAnimation = 10;

    public byte direction;
    final static public byte DIRECTION_UP = 0;
    final static public byte DIRECTION_LEFT = 1;
    final static public byte DIRECTION_DOWN = 2;
    final static public byte DIRECTION_RIGHT = 3;
    public boolean isMoving;

    public Rectangle collisionBox = new Rectangle();
    public int absolutePosX = 0;
    public int absolutePosY = 0;
    public Rectangle absoluteRectPosition = new Rectangle();

    public abstract void setDefaultValues();
    public abstract void update();

    public void setImage(){

        // ANIMATION :
        if (isMoving){
            frameCount ++;
            if (frameCount > frameBetweenAnimation){
                frameCount = 0;
                if (spriteSheetX == 0){
                    spriteSheetX = spriteSheetTileSizeX;
                }
                else {
                    spriteSheetX = 0;
                }
            }
        }
        else {
            spriteSheetX = 0;
        }

        // DIRECTION :
        switch (direction){
            case DIRECTION_UP:{
                spriteSheetY = 0;
                break;
            }
            case DIRECTION_LEFT:{
                spriteSheetY = spriteSheetTileSizeY;
                break;
            }
            case DIRECTION_DOWN:{
                spriteSheetY = spriteSheetTileSizeY * 2;
                break;
            }
            // DIRECTION_RIGHT :
            default:{
                spriteSheetY = spriteSheetTileSizeY * 3;
                break;
            }
        }
    }
    public void draw(Graphics2D g2){
        setImage();
        g2.drawImage(spritesheet,x,y,x + sizeX,y + sizeY, spriteSheetX, spriteSheetY, spriteSheetX + 16, spriteSheetY + 16, null);
    }

}
