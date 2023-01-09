package pizzagame.entity;

import pizzagame.Client;
import pizzagame.Game;

import java.awt.*;

public class StatisticsDisplayEntity extends Entity {
    private int ticks = 0;


    public StatisticsDisplayEntity(Game game) {
        super(game);
        this.z = 50;
    }

    @Override
    public int getHeight() {
        return 512;
    }

    @Override
    public int getWidth() {
        return 220;
    }

    @Override
    public void tick() {
        ticks++;
        if (ticks < 60) {
            this.setX((int)(1920 - (getWidth() + 32) * getAnimationProgress(ticks / 60.0)));
        } else if (ticks > 300) {
            this.setX(this.getX()+4);
        }
        if (x > 1920) {
            discard();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(new Color(0,0,0,125));
        g.setFont(Client.FONT.deriveFont(15f));
        g.fillRect(x, y, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        int cY = y + g.getFontMetrics().getHeight();
        g.drawString("ESTATISTICAS", x, cY);
        cY += g.getFontMetrics().getHeight() + 12;
        g.drawString("Pedidos corretos", x, cY);
        cY += g.getFontMetrics().getHeight() + 4;
        g.drawString("" + game.getStatistics().correctOrders, x, cY);

        cY += g.getFontMetrics().getHeight() + 12;
        g.drawString("Pedidos errados", x, cY);
        cY += g.getFontMetrics().getHeight() + 4;
        g.drawString("" + game.getStatistics().wrongOrders, x, cY);

        cY += g.getFontMetrics().getHeight() + 12;
        g.drawString("Pedidos atrasados", x, cY);
        cY += g.getFontMetrics().getHeight() + 4;
        g.drawString("" + game.getStatistics().timedOutOrders, x, cY);

        cY += g.getFontMetrics().getHeight() + 12;
        g.drawString("Dinheiro gasto", x, cY);
        cY += g.getFontMetrics().getHeight() + 4;
        g.drawString("" + game.getStatistics().moneySpent, x, cY);

        cY += g.getFontMetrics().getHeight() + 12;
        g.drawString("Dinheiro ganho", x, cY);
        cY += g.getFontMetrics().getHeight() + 4;
        g.drawString("" + game.getStatistics().moneyGained, x, cY);

    }

    private double getAnimationProgress(double x) {
        double n1 = 7.5625;
        double d1 = 2.75;

        if (x < 1 / d1) {
            return n1 * x * x;
        } else if (x < 2 / d1) {
            return n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            return n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            return n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
    }
}
