package pizzagame.entity;

import pizzagame.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaEntity extends Entity {

    public PizzaEntity() {
        this.sprite = new Sprite("/assets/mini_icon.png", 128, 128);
    }
    private List<IngredientEntity> ingredients = new ArrayList<>();

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.WHITE);
        g.drawString("PIZZA HAS: ", getX(), getY());
        for (int i = 0; i < ingredients.size(); i++) {
            g.drawString(ingredients.get(i).getName(), getX(), getY()+(i+1)*20);
        }
    }
}
