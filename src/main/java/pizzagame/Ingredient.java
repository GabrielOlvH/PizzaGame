package pizzagame;

import pizzagame.entity.IngredientEntity;

public enum Ingredient {
    MUSHROOM("Mushroom",  new Sprite("/assets/ingredients/mushroom.png", (int)(96*.66), (int)(96*.66))),
    OLIVE("Olive",  new Sprite("/assets/ingredients/olive.png", (int)(96*.66), (int)(96*.66))),
    CHEESE("Cheese",  new Sprite("/assets/ingredients/cheese.png", (int)(96*.66), (int)(96*.66))),
    ONION("Onion",  new Sprite("/assets/ingredients/onion.png", (int)(128*.66), (int)(96*.66))),
    PURPLE_ONION("Purple Onion",  new Sprite("/assets/ingredients/purple_onion.png", (int)(96*.66), (int)(96*.66))),
    RED_PEPPER("Red Pepper",  new Sprite("/assets/ingredients/red_pepper.png", (int)(128*.66), 64)),
    TOMATO("Tomato",  new Sprite("/assets/ingredients/tomato.png", (int)(128*.66), (int)(96*.66))),
    TURNIP("Turnip",  new Sprite("/assets/ingredients/turnip.png", (int)(128*.66), 64)),
    FISH("Fish",  new Sprite("/assets/ingredients/fish.png", (int)(96*.66), (int)(96*.66))),
    AVOCADO("Avocado",  new Sprite("/assets/ingredients/avocado.png", (int)(96*.66), (int)(128*.66))),
    SHRIMP("Shrimp",  new Sprite("/assets/ingredients/shrimp.png", (int)(96*.66), (int)(96*.66))),
    BASIL("Basil",  new Sprite("/assets/ingredients/basil.png", (int)(96*.66), (int)(128*.66))),
    PEPPERONI("Pepperoni",  new Sprite("/assets/ingredients/pepperoni.png", (int)(96*.66), (int)(96*.66))),
    RED_BELL_PEPPER("Red Bell Pepper", new Sprite("/assets/ingredients/bell_pepper.png",(int)(96*.66),(int)(116*.66))),
    YELLOW_BELL_PEPPER("Yellow Bell Pepper",new Sprite("/assets/ingredients/yellow_bell_pepper.png",(int)(96*.66),(int)(116*.66))),
    MARSHMELLOW("Marshmellow",new Sprite("/assets/ingredients/marshmellow.png",(int)(96*.66),(int)(96*.66))),
    STRAWBERRY("Strawberry",new Sprite("/assets/ingredients/morango.png",(int)(84*.66),(int)(84*.66)));



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

}
