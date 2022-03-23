package java_folder.game_engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public static final byte LEFT_BUTTON = (byte)0;
    public static final byte RIGHT_BUTTON = (byte)1;
    public static final byte MIDDLE_BUTTON = (byte)2;

    public int[] positionClickXArray = {0,0,0};
    public int[] positionClickYArray = {0,0,0};

    public boolean[] pressedButtonArray = {false,false,false};
    public int[] positionPressedXArray = {0,0,0};
    public int[] positionPressedYArray = {0,0,0};

    public int[] positionReleaseXArray = {0,0,0};
    public int[] positionReleaseYArray = {0,0,0};

    public boolean isOnScreen = false;

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1){
            positionClickXArray[LEFT_BUTTON] = e.getX();
            positionClickYArray[LEFT_BUTTON] = e.getY();
        }

        if (e.getButton() == MouseEvent.BUTTON3){
            positionClickXArray[RIGHT_BUTTON] = e.getX();
            positionClickYArray[RIGHT_BUTTON] = e.getY();
        }

        if (e.getButton() == MouseEvent.BUTTON2){
            positionClickXArray[MIDDLE_BUTTON] = e.getX();
            positionClickYArray[MIDDLE_BUTTON] = e.getY();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1){
            pressedButtonArray[LEFT_BUTTON] = true;
            positionPressedXArray[LEFT_BUTTON] = e.getX();
            positionPressedYArray[LEFT_BUTTON] = e.getY();
        }

        if (e.getButton() == MouseEvent.BUTTON3){
            pressedButtonArray[RIGHT_BUTTON] = true;
            positionPressedXArray[RIGHT_BUTTON] = e.getX();
            positionPressedYArray[RIGHT_BUTTON] = e.getY();
        }

        if (e.getButton() == MouseEvent.BUTTON2){
            pressedButtonArray[MIDDLE_BUTTON] = true;
            positionPressedXArray[MIDDLE_BUTTON] = e.getX();
            positionPressedYArray[MIDDLE_BUTTON] = e.getY();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1){
            pressedButtonArray[LEFT_BUTTON] = false;
            positionReleaseXArray[LEFT_BUTTON] = e.getX();
            positionReleaseYArray[LEFT_BUTTON] = e.getY();
        }

        if (e.getButton() == MouseEvent.BUTTON3){
            pressedButtonArray[RIGHT_BUTTON] = false;
            positionReleaseXArray[RIGHT_BUTTON] = e.getX();
            positionReleaseYArray[RIGHT_BUTTON] = e.getY();
        }

        if (e.getButton() == MouseEvent.BUTTON2){
            pressedButtonArray[MIDDLE_BUTTON] = false;
            positionReleaseXArray[MIDDLE_BUTTON] = e.getX();
            positionReleaseYArray[MIDDLE_BUTTON] = e.getY();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isOnScreen = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isOnScreen = false;
    }
}
