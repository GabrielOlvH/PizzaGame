package pizzagame;

import java.util.List;

public class Order {
    private List<Ingredient> ingredients;
    private int ticksRemaining;
    private String clientName;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getTicksRemaining() {
        return ticksRemaining;
    }

    public String getClientName() {
        return clientName;
    }
}
