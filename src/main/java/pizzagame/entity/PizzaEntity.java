package pizzagame.entity;

import pizzagame.Ingredient;
import pizzagame.PizzaType;
import pizzagame.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaEntity extends DraggableEntity {

    public PizzaEntity() {
        this.sprite = new Sprite("/assets/empty_pizza.png", 688/2, 438/2);
    }

    public void setTypeSprite(PizzaType type) {
        this.sprite = type.getSprite();
    }

    private final List<Ingredient> ingredients = new ArrayList<>();

    public List<Ingredient> getIngredients() {
        return ingredients;
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
        return false;
    }
}
