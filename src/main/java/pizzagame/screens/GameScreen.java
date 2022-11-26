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
    private JButton addPizzaButton;

    public GameScreen(Game game) {
        this.game = game;
        setLayout(null);
        GameMouseListener mouseListener = new GameMouseListener(game);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        this.backToMenu = new MenuButton("MENU");
        this.backToMenu.setFont(Client.FONT.deriveFont(30f));
        this.backToMenu.setBounds(game.getClient().getWidth() - 450, 0, 450, 100);
        this.backToMenu.addActionListener((e) -> {
            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                game.getClient().stopGame(new MenuScreen(game.getClient()));
            }
        });
        add(this.backToMenu);

        this.addPizzaButton = new MenuButton("NEW PIZZA");
        this.addPizzaButton.setFont(Client.FONT.deriveFont(30f));
        this.addPizzaButton.setBounds(game.getClient().getWidth() - 600, 500, 450, 100);
        this.addPizzaButton.addActionListener((e) -> {
            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                game.addPizzaDough();
            }
        });
        add(this.addPizzaButton);
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

        g.setFont(Client.FONT.deriveFont(12f));
        g.setColor(Color.BLACK);
        g.drawString("MONEY: " + game.getMoney(), 1700, 200);
    }
}
