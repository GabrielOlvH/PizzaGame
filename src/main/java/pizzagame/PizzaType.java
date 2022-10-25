package pizzagame;

public enum PizzaType {
    EMPTY(new Sprite("/assets/empty_pizza.png", 688/2, 438/2)),
    CHEESE(new Sprite("/assets/cheese_pizza.png", 688/2, 438/2)),
    TOMATO(new Sprite("/assets/tomato_pizza.png", 688/2, 438/2)),
    CHOCOLATE(new Sprite("/assets/chocolate_pizza.png", 688/2, 438/2));
    private Sprite sprite;

    PizzaType(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
