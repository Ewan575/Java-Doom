package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

    public static volatile boolean wPressed = false;
    public static volatile boolean aPressed = false;
    public static volatile boolean sPressed = false;
    public static volatile boolean dPressed = false;
    public static volatile boolean leftPressed = false;
    public static volatile boolean rightPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        set(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        set(e.getKeyCode(), false);
    }

    private void set(int key, boolean down) {
        switch (key) {
            case KeyEvent.VK_W     -> wPressed = down;
            case KeyEvent.VK_S     -> sPressed = down;
            case KeyEvent.VK_A     -> aPressed = down;
            case KeyEvent.VK_D     -> dPressed = down;
            case KeyEvent.VK_LEFT  -> leftPressed = down;
            case KeyEvent.VK_RIGHT -> rightPressed = down;
        }
    }
}