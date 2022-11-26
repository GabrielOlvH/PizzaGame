package pizzagame.entity;

import pizzagame.Client;
import pizzagame.Game;
import pizzagame.Ingredient;
import pizzagame.Sprite;

import java.awt.*;

public class IngredientEntity extends DraggableEntity {

    private Ingredient ingredient = null;

    public IngredientEntity(Game game) {
        super(game);
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        if (ingredient != null)
            this.sprite = ingredient.getSprite();
    }

    public boolean isEmpty() {
        return ingredient == null || !game.getAvailableIngredients().containsKey(ingredient);
    }

    public int getCount() {
        return game.getAvailableIngredients().get(ingredient);
    }

    @Override
    public boolean dropInto(Entity entity) {
        if (!isEmpty() && entity instanceof PizzaEntity pizza && pizza.addIngredient(ingredient)) {
            int count = getCount() - 1;
            game.getAvailableIngredients().put(ingredient, count);
            if (count <= 0) {
                game.getAvailableIngredients().remove(ingredient);
                game.updateIngredients();
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        if (isEmpty()) return;
        super.draw(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(Client.FONT.deriveFont(20f));
        g.setColor(Color.BLACK);
        g.drawString(getCount()+"", getX() + getWidth() - g.getFontMetrics().stringWidth(getCount()+""), getY() + getHeight());
    }

    @Override
    public void drawMouseOver(Graphics g, Point p) {
        if (isEmpty()) return;
        g.setColor(new Color(0,0,0,125));
        g.setFont(Client.FONT.deriveFont(15f));
        g.fillRect(p.x-8, p.y, g.getFontMetrics().stringWidth(ingredient.getName()) + 16, g.getFontMetrics().getHeight() + 16);
        g.setColor(Color.WHITE);

        g.drawString(ingredient.getName(), p.x, p.y + g.getFontMetrics().getHeight());
    }
}
