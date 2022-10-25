package pizzagame.entity;

import pizzagame.Client;
import pizzagame.Ingredient;
import pizzagame.PizzaType;
import pizzagame.Sprite;

import java.awt.*;

public class SauceJarEntity extends DraggableEntity{
    private final PizzaType type;

    public SauceJarEntity(Sprite sprite, PizzaType type) {
        this.sprite = sprite;
        this.type = type;
    }

    @Override
    public boolean dropInto(Entity entity) {
        if (entity instanceof PizzaEntity pizza) {
            pizza.setTypeSprite(type);
        }
        return false;
    }

    @Override
    public void drawMouseOver(Graphics g, Point p) {

        String text = "";

        switch (type) {
            case EMPTY -> {}
            case CHEESE ->text = "Cheese Sauce";
            case TOMATO -> text = "Tomato Sauce";
            case CHOCOLATE -> text = "Chocolate Sauce";
        }

        g.setColor(new Color(0,0,0,125));
        g.setFont(Client.FONT.deriveFont(15f));
        g.fillRect(p.x-8, p.y, g.getFontMetrics().stringWidth(text) + 16, g.getFontMetrics().getHeight() + 16);
        g.setColor(Color.WHITE);
        g.drawString(text, p.x, p.y + g.getFontMetrics().getHeight());
    }
}
