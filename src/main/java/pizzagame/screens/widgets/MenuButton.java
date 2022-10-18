package pizzagame.screens.widgets;

import pizzagame.Client;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String text) {
        super(text);
        setFont(Client.FONT.deriveFont(30f));
        setBorder(null);
        setBackground(new Color(0f, 0f, 0f, 0f));
        setFocusable(false);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setRolloverEnabled(false);
    }
}
