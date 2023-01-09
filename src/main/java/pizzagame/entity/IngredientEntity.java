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
    public void onClick(int x, int y) {
        if (game.isShopMode() && game.getMoney() >= 5) {
            game.takeMoney(5);
            int amt = game.getAvailableIngredients().getOrDefault(ingredient, 0) + 1;
            game.getAvailableIngredients().put(ingredient, amt);
            game.getStatistics().ingredientsBought++;
        }
    }

    @Override
    public boolean canDrag() {
        return !game.isShopMode();
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

        if (game.isShopMode()) {
            g.setColor(new Color(0,0,0,125));
            int width = g.getFontMetrics().stringWidth("$5");
            g.fillRect(getX() + getWidth() / 2 - 8 - width/2, getY() + getHeight() / 2 - g.getFontMetrics().getHeight() , width + 16, g.getFontMetrics().getHeight() + 16);
            if (game.getMoney() >= 5)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.RED);
            g.drawString("$5", getX() + getWidth() / 2 - width/2 , getY() + getHeight() / 2);
        }
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
