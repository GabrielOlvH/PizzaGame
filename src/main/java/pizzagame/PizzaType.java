package pizzagame;

public enum PizzaType {
    EMPTY("Sem molho", new Sprite("/assets/empty_pizza.png", (int)(688/2*.66), (int)(438/2*.66))),
    CHEESE("Molho de Queijo", new Sprite("/assets/cheese_pizza.png", (int)(688/2*.66), (int)(438/2*.66))),
    TOMATO("Molho de Tomate", new Sprite("/assets/tomato_pizza.png", (int)(688/2*.66), (int)(438/2*.66))),
    CHOCOLATE("Molho de Chocolate", new Sprite("/assets/chocolate_pizza.png", (int)(688/2*.66), (int)(438/2*.66)));

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
