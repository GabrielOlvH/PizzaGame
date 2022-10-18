package pizzagame.screens;

import pizzagame.Client;
import pizzagame.Game;
import pizzagame.Sprite;
import pizzagame.listeners.GameMouseListener;
import pizzagame.screens.widgets.DraggableButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JPanel {
    private static final Sprite GAME_BACKGROUND = new Sprite("/assets/game.png", 1920, 1080);

    private final Game game;
    private JButton backToMenu;

    public GameScreen(Game game) {
        this.game = game;
        setLayout(null);
        GameMouseListener mouseListener = new GameMouseListener(game);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        this.backToMenu = new DraggableButton();
        this.backToMenu.setText("TEST");
        this.backToMenu.setFont(Client.FONT.deriveFont(30f));
        this.backToMenu.setBounds(0, 0, 100, 100);
        add(this.backToMenu);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GAME_BACKGROUND.getImage(), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
        new ArrayList<>(game.getEntities()).forEach(e -> e.draw(g));
    }
}
