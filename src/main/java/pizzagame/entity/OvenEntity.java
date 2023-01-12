package pizzagame.entity;

import pizzagame.Client;
import pizzagame.Game;

import java.awt.*;

public class OvenEntity extends Entity {

    private boolean isUnlocked = true;
    private PizzaEntity cooking;
    public OvenEntity(Game game) {
        super(game);
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public PizzaEntity getCooking() {
        return cooking;
    }

    public void startCooking(PizzaEntity cooking) {
        this.cooking = cooking;
    }

    @Override
    public int getWidth() {
        return (int)((128+32)*.66);
    }

    @Override
    public int getHeight() {
        return (int)(128*.66);
    }

    @Override
    public void tick() {
        if (cooking != null)
            cooking.setCookingTime(cooking.getCookingTime() + 1);
    }

    @Override
    public void onClick(int x, int y) {
        if (!isUnlocked && game.isShopMode() && game.getMoney() >= 300) {
            this.isUnlocked = true;
            game.takeMoney(300);
        }
        if (cooking != null) {
            cooking.setPos(getX() - getWidth() / 2, getY());
            cooking.discarded = false;
            cooking.setZ(5);
            game.getEntities().add(cooking);
            cooking = null;
        }
    }

    @Override
    public void draw(Graphics g) {

        g.setFont(g.getFont().deriveFont(15f*.66f));
        g.setColor(new Color(0,0,0,125));
        g.fillRect(getX(), getY() - g.getFontMetrics().getHeight()/2 - 10, getWidth(), g.getFontMetrics().getHeight());
        g.setColor(Color.WHITE);
        if (!isUnlocked()) {
            if (game.isShopMode()) {
                g.drawString("Desbloquear", getX() - g.getFontMetrics().stringWidth("Desbloquear")/2 + getWidth() / 2, getY());
                g.drawString("$300", getX() - g.getFontMetrics().stringWidth("$300")/2 + getWidth() / 2, getY() + 22);
            } else {
                g.drawString("Bloqueado!", getX() - g.getFontMetrics().stringWidth("Bloqueado!")/2 + getWidth() / 2, getY());
            }
        }
        else if (cooking != null) {
            g.drawString("Cozinhando...", getX() - g.getFontMetrics().stringWidth("Cozinhando...")/2 + getWidth() / 2, getY());
            g.fillRect(getX(), getY() + 9, getWidth(), 16);
            if (cooking.isCookingPointGood())
                g.setColor(Color.GREEN);
            else if (cooking.getCookingTime() >= 360)
                g.setColor(Color.RED);
            else
                g.setColor(Color.YELLOW);
            float p = (Math.min(cooking.getCookingTime(), 360.0f) / 360.0f);
            g.fillRect(getX(), getY() + 9, (int) (getWidth() * p), 16);
        } else {
            g.drawString("Cozinhar!", getX() - g.getFontMetrics().stringWidth("Cozinhar!")/2 + getWidth() / 2, getY());
        }
    }
}
