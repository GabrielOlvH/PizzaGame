package pizzagame.entity;

import pizzagame.Game;

import java.awt.*;

public class FadeOutTextEntity extends Entity {

    public String text;
    public int color;
    private int ticksRemaining = 60;

    public FadeOutTextEntity(Game game, String text, int color) {
        super(game);
        this.text = text;
        this.color = color;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void tick() {
        ticksRemaining--;
        if (ticksRemaining <= 0) {
            discard();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (color != -1) {
            g.setColor(new Color(color));
        }
        g.setFont(g.getFont().deriveFont(12f));
        int alpha = (int) (255 * (ticksRemaining / 60.0));
        Color c = g.getColor();
        g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();
        g.drawString(text, getX() - width / 2, (getY() - height/ 2) +  (int) (20 * (ticksRemaining / 60.0)) -20);
    }
}
