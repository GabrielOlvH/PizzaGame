package pizzagame;

import pizzagame.entity.IngredientEntity;

public enum Ingredient {
    MUSHROOM("Mushroom",  new Sprite("/assets/ingredients/mushroom.png", 96, 96)),
    OLIVE("Olive",  new Sprite("/assets/ingredients/olive.png", 96, 96)),
    CHEESE("Cheese",  new Sprite("/assets/ingredients/cheese.png", 96, 96)),
    ONION("Onion",  new Sprite("/assets/ingredients/onion.png", 128, 96)),
    PURPLE_ONION("Purple Onion",  new Sprite("/assets/ingredients/purple_onion.png", 96, 96)),
    RED_PEPPER("Red Pepper",  new Sprite("/assets/ingredients/red_pepper.png", 128, 64)),
    TOMATO("Tomato",  new Sprite("/assets/ingredients/tomato.png", 128, 96)),
    TURNIP("Turnip",  new Sprite("/assets/ingredients/turnip.png", 128, 64)),
    FISH("Fish",  new Sprite("/assets/ingredients/fish.png", 96, 96)),
    AVOCADO("Avocado",  new Sprite("/assets/ingredients/avocado.png", 96, 128)),
    SHRIMP("Shrimp",  new Sprite("/assets/ingredients/shrimp.png", 96, 96)),
    BASIL("Basil",  new Sprite("/assets/ingredients/basil.png", 96, 128)),
    PEPPERONI("Pepperoni",  new Sprite("/assets/ingredients/pepperoni.png", 96, 96)),
    RED_BELL_PEPPER("Red Bell Pepper", new Sprite("/assets/ingredients/bell_pepper.png",96,116)),
    YELLOW_BELL_PEPPER("Yellow Bell Pepper",new Sprite("/assets/ingredients/yellow_bell_pepper.png",96,116)),
    MARSHMELLOW("Marshmellow",new Sprite("/assets/ingredients/marshmellow.png",96,96));
    //STRAWBERRY("Strawberry",new Sprite("/assets/ingredients/strawberry.png",142,96));//



    public static final Ingredient[] ALL = Ingredient.values();
    private final String name;
    private final Sprite sprite;
    Ingredient(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public IngredientEntity createEntity(Game game, int x, int y, int count) {
        IngredientEntity entity = new IngredientEntity(game, this, sprite, count);
        entity.setX(x);
        entity.setY(y);
        return entity;
    }
}
