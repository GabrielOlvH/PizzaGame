package pizzagame;

import pizzagame.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {

    public static Font FONT;

    private Game game;
    private Thread mainThread;

    public void start() {
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
        device.setDisplayMode(new DisplayMode(1280, 720, 32, 60));

        setVisible(true);
        repaint();
    }

    public void setContentPaneFix(JPanel pane) {
        System.setProperty("sun.java2d.noddraw", "true");
        setContentPane(pane);
    }

    public void startGame(String playerName) {
        game = new Game(this, playerName);
        setContentPaneFix(game.getScreen());
        game.getScreen().requestFocus();
        mainThread = new Thread(() -> {
            while (true) {
                try {
                    long timeElapsed = System.currentTimeMillis();
                    game.tick();
                    timeElapsed = System.currentTimeMillis() - timeElapsed;
                    if (timeElapsed < 20) {
                        Thread.sleep(20 - timeElapsed);
                    } else {
                        System.out.println("BEHIND " + timeElapsed + " ms");
                    }
                    game.getScreen().repaint();
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
            setContentPaneFix(newScreen);
        mainThread.stop();
        mainThread = null;
        game = null;
        repaint();
        validate();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Client client = new Client();
            client.start();
        });
    }
}

