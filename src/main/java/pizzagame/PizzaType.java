package pizzagame;

public enum PizzaType {
    EMPTY("No sauce", new Sprite("/assets/empty_pizza.png", 688/2, 438/2)),
    CHEESE("Cheese Sauce", new Sprite("/assets/cheese_pizza.png", 688/2, 438/2)),
    TOMATO("Tomato Sauce", new Sprite("/assets/tomato_pizza.png", 688/2, 438/2)),
    CHOCOLATE("Chocolate Sauce", new Sprite("/assets/chocolate_pizza.png", 688/2, 438/2));

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
