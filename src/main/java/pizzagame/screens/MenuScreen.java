package pizzagame.screens;

import pizzagame.Client;
import pizzagame.Sprite;
import pizzagame.screens.widgets.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuScreen extends JPanel {
    private static final Sprite MENU_BACKGROUND = new Sprite("/assets/menu.png", 1920, 1080);
    private static final Sprite HANGING_SIGN = new Sprite("/assets/hanging_sign.png", 1024, 1024);
    private static final Sprite SMALL_SIGN = new Sprite("/assets/small_sign.png", 1024, 1024);

    private final JButton play;
    private final JButton close;

    public MenuScreen(Client client) {
        setOpaque(false);
        setLayout(null);
        play = new MenuButton("Jogar");
        play.setFont(Client.FONT.deriveFont(30f*.66f));
        play.setForeground(new Color(88, 2, 16));
        play.setBounds(890-(int)(890*.33), 495-(int)(495*.33), (int)(280*.66), (int)(80*.66));
        //play.setIcon(new ImageIcon(new Sprite("/assets/icon.png", 128, 128).getImage()));
        play.addActionListener(e -> {
            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                client.startGame("aaa");
            }

        });
        play.setVisible(true);
        add(play);

        close = new MenuButton("X");
        close.setFont(Client.FONT.deriveFont(50f*.66f));
        close.setBounds(1200, 10, 100, 100);
        close.setForeground(Color.WHITE);
        close.addActionListener(e -> {
            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                System.exit(0);
            }
        });
        add(close);

        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MENU_BACKGROUND.getImage(), 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);

        g.drawImage(SMALL_SIGN.getImage(), 800-(int)(800*.33), 160-(int)(160*.33), (int)(512*0.66), (int)(512*.66), null);
        g.drawImage(HANGING_SIGN.getImage(), 350-(int)(300*.33), -400 -(int) (-400*.33), (int)(1024*.66), (int)(1024*.66), null);

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(Client.FONT.deriveFont(Font.ITALIC, 70f*.66f));
        g.setColor(new Color(88, 2, 16));
        g.drawString("PizzIA", 570-(int)(570*.33), 250-(int)(250*.33));
        g.drawString("Simulator", 600-(int)(600*.33), 375-(int)(375*.33));


    }
}
