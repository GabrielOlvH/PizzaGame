package pizzagame;

import pizzagame.entity.IngredientEntity;

public enum Ingredient {
    MUSHROOM("Cogumelo",  new Sprite("/assets/ingredients/mushroom.png", (int)(96*.66), (int)(96*.66))),
    OLIVE("Azeitona",  new Sprite("/assets/ingredients/olive.png", (int)(96*.66), (int)(96*.66))),
    CHEESE("Cheese",  new Sprite("/assets/ingredients/cheese.png", (int)(96*.66), (int)(96*.66))),
    ONION("Cebola",  new Sprite("/assets/ingredients/onion.png", (int)(128*.66), (int)(96*.66))),
    PURPLE_ONION("Cebola Roxa",  new Sprite("/assets/ingredients/purple_onion.png", (int)(96*.66), (int)(96*.66))),
    RED_PEPPER("Pimenta",  new Sprite("/assets/ingredients/red_pepper.png", (int)(128*.66), 64)),
    TOMATO("Tomate",  new Sprite("/assets/ingredients/tomato.png", (int)(128*.66), (int)(96*.66))),
    TURNIP("Nabo",  new Sprite("/assets/ingredients/turnip.png", (int)(128*.66), 64)),
    FISH("Peixe",  new Sprite("/assets/ingredients/fish.png", (int)(96*.66), (int)(96*.66))),
    AVOCADO("Abacate",  new Sprite("/assets/ingredients/avocado.png", (int)(96*.66), (int)(128*.66))),
    SHRIMP("Camarão",  new Sprite("/assets/ingredients/shrimp.png", (int)(96*.66), (int)(96*.66))),
    BASIL("Manjericão",  new Sprite("/assets/ingredients/basil.png", (int)(96*.66), (int)(128*.66))),
    PEPPERONI("Pepperoni",  new Sprite("/assets/ingredients/pepperoni.png", (int)(96*.66), (int)(96*.66))),
    RED_BELL_PEPPER("Pimentão Vermelho", new Sprite("/assets/ingredients/bell_pepper.png",(int)(96*.66),(int)(116*.66))),
    YELLOW_BELL_PEPPER("Pimentão Amarelo",new Sprite("/assets/ingredients/yellow_bell_pepper.png",(int)(96*.66),(int)(116*.66))),
    MARSHMELLOW("Marshmellow",new Sprite("/assets/ingredients/marshmellow.png",(int)(96*.66),(int)(96*.66))),
    STRAWBERRY("Morango",new Sprite("/assets/ingredients/morango.png",(int)(84*.66),(int)(84*.66)));



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
