package pizzagame;

public enum PizzaType {
    EMPTY("No sauce", new Sprite("/assets/empty_pizza.png", (int)(688/2*.66), (int)(438/2*.66))),
    CHEESE("Cheese Sauce", new Sprite("/assets/cheese_pizza.png", (int)(688/2*.66), (int)(438/2*.66))),
    TOMATO("Tomato Sauce", new Sprite("/assets/tomato_pizza.png", (int)(688/2*.66), (int)(438/2*.66))),
    CHOCOLATE("Chocolate Sauce", new Sprite("/assets/chocolate_pizza.png", (int)(688/2*.66), (int)(438/2*.66)));

    public static final PizzaType[] VALID_TYPES = { CHEESE, TOMATO, CHOCOLATE };
    private Sprite sprite;
    private String name;

    PizzaType(String name, Sprite sprite) {
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
