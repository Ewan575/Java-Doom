package main;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(GamePanel panel) {
        setTitle("Ewan's DOOM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(panel);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
        panel.requestFocusInWindow();
    }
}
