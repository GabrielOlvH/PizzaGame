package pizzagame.entity;

import pizzagame.Client;
import pizzagame.Ingredient;
import pizzagame.Sprite;

import java.awt.*;

public class IngredientEntity extends DraggableEntity {

    private final Ingredient ingredient;
    private int count;

    public IngredientEntity(Ingredient ingredient, Sprite sprite, int count) {
        this.sprite = sprite;
        this.ingredient = ingredient;
        this.count = count;
    }

    @Override
    public boolean dropInto(Entity entity) {
        if (entity instanceof PizzaEntity pizza) {
            pizza.getIngredients().add(ingredient);
            count--;
            if (count <= 0) {
                discard();
            } else {
                setX(getOriginalX());
                setY(getOriginalY());
            }
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(Client.FONT.deriveFont(20f));
        g.drawString(count+"", getX() + getWidth() - g.getFontMetrics().stringWidth(count+""), getY() + getHeight());
    }

    @Override
    public void drawMouseOver(Graphics g, Point p) {
        g.setColor(new Color(0,0,0,125));
        g.setFont(Client.FONT.deriveFont(15f));
        g.fillRect(p.x-8, p.y, g.getFontMetrics().stringWidth(ingredient.getName()) + 16, g.getFontMetrics().getHeight() + 16);
        g.setColor(Color.WHITE);

        g.drawString(ingredient.getName(), p.x, p.y + g.getFontMetrics().getHeight());
    }
}
