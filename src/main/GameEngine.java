package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine implements ActionListener {

    private static final double RADIUS = 0.25;      // tiles
    private static final double MOVE_SPEED = 0.05;  // tiles per frame
    private static final double ROT_SPEED = 0.05;   // radians per frame

    private double playerX = 1.5;
    private double playerY = 1.5;

    private final GamePanel panel;

    double dirX = 1;
    double dirY = 0;

    double planeX = 0;
    double planeY = 0.66;

    public void init() {
        System.out.println("Initializing Engine");

        Timer timer = new Timer(16, this);
        timer.start();
    }

    public GameEngine(GamePanel panel) {
        this.panel = panel;
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    private void update() {
        double moveX = 0, moveY = 0;

        if (Listener.wPressed) { moveX += dirX; moveY += dirY; }
        if (Listener.sPressed) { moveX -= dirX; moveY -= dirY; }
        if (Listener.dPressed) { moveX += -dirY; moveY += dirX; }
        if (Listener.aPressed) { moveX -= -dirY; moveY -= dirX; }

        double len = Math.hypot(moveX, moveY);
        if (len > 0) {
            moveX = moveX / len * MOVE_SPEED;
            moveY = moveY / len * MOVE_SPEED;
        }

        if (!collides(playerX + moveX, playerY)) playerX += moveX;
        if (!collides(playerX, playerY + moveY)) playerY += moveY;

        if (Listener.leftPressed)  rotate(-ROT_SPEED);
        if (Listener.rightPressed) rotate(ROT_SPEED);
    }



    public void render() {
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLoop();
    }

    public void gameLoop() {
        update();
        render();
    }

    private void rotate(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double oldDirX = dirX;
        dirX = dirX * cos - dirY * sin;
        dirY = oldDirX * sin + dirY * cos;

        double oldPlaneX = planeX;
        planeX = planeX * cos - planeY * sin;
        planeY = oldPlaneX * sin + planeY * cos;
    }

    private boolean isWall(double x, double y) {
        int tileX = (int) Math.floor(x);
        int tileY = (int) Math.floor(y);

        if (tileX < 0 || tileY < 0 || tileY >= panel.map.length || tileX >= panel.map[0].length) {
            return true;
        }
        return panel.map[tileY][tileX] != 0;   // [row][col]
    }

    private boolean collides(double x, double y) {
        return isWall(x + RADIUS, y + RADIUS) ||
                isWall(x - RADIUS, y + RADIUS) ||
                isWall(x + RADIUS, y - RADIUS) ||
                isWall(x - RADIUS, y - RADIUS);
    }

}