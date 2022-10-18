package pizzagame.entity;

import pizzagame.Sprite;

public class IngredientEntity extends DraggableEntity {

    private final String name;
    public IngredientEntity(String name, Sprite sprite) {
        this.sprite = sprite;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean dropInto(Entity entity) {
        if (entity instanceof PizzaEntity pizza) {
            this.setX(-100);
            this.setY(-100);
            pizza.getIngredients().add(this);
            return true;
        }
        return false;
    }
}
