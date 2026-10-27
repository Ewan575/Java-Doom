package main;

public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        GameEngine engine = new GameEngine(gamePanel);
        gamePanel.setEngine(engine);

        new GameFrame(gamePanel);
        engine.init();
    }
}
