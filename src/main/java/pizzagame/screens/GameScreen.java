package pizzagame.screens;

import pizzagame.Client;
import pizzagame.Game;
import pizzagame.Sprite;
import pizzagame.listeners.GameMouseListener;
import pizzagame.screens.widgets.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GameScreen extends JPanel {
    private static final Sprite GAME_BACKGROUND = new Sprite("/assets/new_game.png", 1920, 1080);

    private final Game game;
    private JButton backToMenu;

    public GameScreen(Game game) {
        this.game = game;
        setLayout(null);
        GameMouseListener mouseListener = new GameMouseListener(game);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        this.backToMenu = new MenuButton("BACK TO MENU");
        this.backToMenu.setFont(Client.FONT.deriveFont(30f));
        this.backToMenu.setBounds(game.getClient().getWidth() - 450, 0, 450, 100);
        this.backToMenu.addActionListener((e) -> {
            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                game.getClient().stopGame(new MenuScreen(game.getClient()));
            }
        });
        add(this.backToMenu);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GAME_BACKGROUND.getImage(), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
        new ArrayList<>(game.getEntities()).forEach(e -> e.draw(g));
        Point p = MouseInfo.getPointerInfo().getLocation();
        new ArrayList<>(game.getEntities()).forEach(e -> {
            if (e.isPointOver(p.x, p.y)) {
                e.drawMouseOver(g, p);
            }
        });
    }
}
