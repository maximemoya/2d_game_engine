package java_folder.game_engine;

import java_folder.game_engine.entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // boolean[]moveKeyState => UP, LEFT, DOWN, RIGHT :
    public boolean[] moveKeyState = {false,false,false,false};
    public boolean isMoving = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            moveKeyState[Entity.DIRECTION_UP] = true;
        }
        if(code == KeyEvent.VK_A){
            moveKeyState[Entity.DIRECTION_LEFT] = true;
        }
        if(code == KeyEvent.VK_S){
            moveKeyState[Entity.DIRECTION_DOWN] = true;
        }
        if(code == KeyEvent.VK_D){
            moveKeyState[Entity.DIRECTION_RIGHT] = true;
        }

        if (moveKeyState[0] || moveKeyState[1] || moveKeyState[2] || moveKeyState[3]){
            isMoving = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            moveKeyState[Entity.DIRECTION_UP] = false;
        }
        if(code == KeyEvent.VK_A){
            moveKeyState[Entity.DIRECTION_LEFT] = false;
        }
        if(code == KeyEvent.VK_S){
            moveKeyState[Entity.DIRECTION_DOWN] = false;
        }
        if(code == KeyEvent.VK_D){
            moveKeyState[Entity.DIRECTION_RIGHT] = false;
        }

        if (!moveKeyState[0] && !moveKeyState[1] && !moveKeyState[2] && !moveKeyState[3]){
            isMoving = false;
        }

    }
}
