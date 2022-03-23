package java_folder;

import java_folder.game_engine.GamePanel;
import java_folder.game_engine.GlobalPanel;
import java_folder.game_engine.OptionPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static int SCALE = 4;

    public static void main (String[] args){
        System.out.println("Hello Java");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("test_window");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        window.setUndecorated(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int SCREEN_WIDTH = dimension.width;
        int SCREEN_HEIGHT = dimension.height;
        Color background_out_color = Color.black;

        if (SCREEN_WIDTH < 800){
            SCALE = 1;
        }
        else if (SCREEN_WIDTH < 1200){
            SCALE = 2;
        }
        else if (SCREEN_WIDTH < 1900){
            SCALE = 3;
        }
        else if (SCREEN_WIDTH < 2000){
            SCALE = 4;
        }
        else if (SCREEN_WIDTH < 2600){
            SCALE = 6;
        }
        else {
            SCALE = 8;
        }

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension((SCREEN_WIDTH - GamePanel.GAME_WIDTH) / 2, GamePanel.GAME_HEIGHT));
        westPanel.setBackground(background_out_color);
        westPanel.setDoubleBuffered(true);
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension((SCREEN_WIDTH - GamePanel.GAME_WIDTH) / 2, GamePanel.GAME_HEIGHT));
        eastPanel.setBackground(background_out_color);
        eastPanel.setDoubleBuffered(true);
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, ((SCREEN_HEIGHT - GamePanel.GAME_HEIGHT) / 2) - OptionPanel.OPTION_HEIGHT));
        southPanel.setBackground(background_out_color);
        southPanel.setDoubleBuffered(true);
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, (SCREEN_HEIGHT - GamePanel.GAME_HEIGHT) / 2));
        northPanel.setBackground(background_out_color);
        northPanel.setDoubleBuffered(true);

//        GamePanel gamePanel = new GamePanel();
        GlobalPanel globalPanel = new GlobalPanel();

        window.add(globalPanel, BorderLayout.CENTER);
        window.add(westPanel, BorderLayout.WEST);
        window.add(eastPanel, BorderLayout.EAST);
        window.add(southPanel, BorderLayout.SOUTH);
        window.add(northPanel, BorderLayout.NORTH);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
