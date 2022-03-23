package java_folder.game_engine;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {

    public static int OPTION_WIDTH = GamePanel.GAME_WIDTH;
    public static int OPTION_HEIGHT = GamePanel.TILE_SIZE;

    public OptionPanel(){

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(OPTION_WIDTH, OPTION_HEIGHT));
        this.setBackground(Color.DARK_GRAY);

        JButton resetBtn = new JButton("RESET MAP");
        resetBtn.setPreferredSize(new Dimension(OPTION_WIDTH/4, OPTION_HEIGHT));
        resetBtn.addActionListener(e -> {
            GamePanel.mapManager.loadMap("Map/newMap.mmap");
            GlobalPanel.gamePanel.requestFocus();
        });
        this.add(resetBtn);

        JButton saveBtn = new JButton("SAVE MAP");
        saveBtn.setPreferredSize(new Dimension(OPTION_WIDTH/4, OPTION_HEIGHT));
        saveBtn.addActionListener(e -> {
            GamePanel.mapManager.saveMap("newMap.mmap");
            GlobalPanel.gamePanel.requestFocus();
        });
        this.add(saveBtn);

    }

}
