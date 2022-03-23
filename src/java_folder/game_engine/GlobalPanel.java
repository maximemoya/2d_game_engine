package java_folder.game_engine;

import javax.swing.*;
import java.awt.*;

public class GlobalPanel extends JPanel {

    public static KeyHandler keyHandler = new KeyHandler();
    public static MouseHandler mouseHandler = new MouseHandler();

    public static OptionPanel optionPanel = new OptionPanel();
    public static GamePanel gamePanel = new GamePanel();

    public GlobalPanel(){

        this.setLayout(new FlowLayout());
        this.setBackground(Color.DARK_GRAY);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.add(optionPanel);
        this.add(gamePanel);

    }

}
