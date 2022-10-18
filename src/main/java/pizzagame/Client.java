package pizzagame;

import pizzagame.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {

    public static Font FONT;

    private Game game;
    private Thread mainThread;

    public void start() {
        System.setProperty("sun.java2d.noddraw", "true");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, Client.class.getResource("/assets/fonts/font.ttf").openStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setTitle("Pizza Game");
        setIconImage(new Sprite("/assets/mini_icon.png", 128, 128).getImage());

        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        GraphicsDevice device = getGraphicsConfiguration().getDevice();

        Rectangle b = device.getDefaultConfiguration().getBounds();
        setSize(b.width, b.height);

        setContentPane(new MenuScreen(this));

        setUndecorated(true);
        pack();
        device.setFullScreenWindow(this);

        setVisible(true);
        repaint();
    }

    public void startGame(String playerName) {
        game = new Game(this, playerName);
        setContentPane(game.getScreen());
        game.getScreen().requestFocus();
        mainThread = new Thread(() -> {
            while (true) {
                try {
                    long timeElapsed = System.currentTimeMillis();
                    game.tick();
                    game.getScreen().repaint();

                    timeElapsed = System.currentTimeMillis() - timeElapsed;
                    if (timeElapsed < 20) {
                        Thread.sleep(20 - timeElapsed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mainThread.start();
        validate();
    }

    public void stopGame(JPanel newScreen) {
        if (newScreen != null)
            setContentPane(newScreen);
        pack();
        setSize(new Dimension(600, 800));
        mainThread.stop();
        mainThread = null;
        game = null;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Client client = new Client();
            client.start();
        });
    }
}

