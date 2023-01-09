package pizzagame.entity;

import pizzagame.Game;
import pizzagame.Ingredient;
import pizzagame.PizzaType;
import pizzagame.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaEntity extends DraggableEntity {

    private PizzaType type;
    private int cookingTime;

    public PizzaEntity(Game game) {
        super(game);
        this.sprite = new Sprite("/assets/empty_pizza.png", 688/2, 438/2);
        this.cookingTime = 0;
    }

    public void setType(PizzaType type) {
        this.type = type;
        this.sprite = type.getSprite();
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public PizzaType getType() {
        return type;
    }

    private final List<Ingredient> ingredients = new ArrayList<>();

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean addIngredient(Ingredient i) {
        if (ingredients.size() >= 6) return false;
        ingredients.add(i);
        return true;
    }

    public boolean isCookingPointGood() {
        return cookingTime > 300 && cookingTime < 360;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (int i = 0; i < ingredients.size(); i++) {
            int x = i % 3 * 64;
            int y = i / 3 * 64;
            Ingredient ing = ingredients.get(i);
            g.drawImage(ing.getSprite().getImage(), getX() + x+64, getY() + y + 32, (int) (ing.getSprite().getWidth() / 1.5), (int)(ing.getSprite().getHeight()/1.5), null);
        }
    }

    @Override
    public boolean dropInto(Entity entity) {
        if (entity instanceof TrashCanEntity) {
            discard();
            game.getStatistics().discardedPizzas++;
            return true;
        } else if (entity instanceof OrderEntity order && order.isActive()) {
            order.deliver(this);
            discard();
            return true;
        } else if (entity instanceof OvenEntity oven && oven.getCooking() == null) {
            oven.startCooking(this);
            discard();
            return true;
        }
        return false;
    }
}
