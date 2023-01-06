package pizzagame.entity;

import pizzagame.*;

import java.awt.*;
import java.util.List;

public class OrderEntity extends Entity {

    public static final int MAX_TIME = 20 * 120;
    private PizzaType type;
    private List<Ingredient> ingredients;
    private int ticksRemaining;
    private String clientName;

    private boolean active;

    public OrderEntity(Game game) {
        super(game);
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setInfo(PizzaType type, List<Ingredient> ingredients) {
        this.type = type;
        this.ingredients = ingredients;
        this.ticksRemaining = MAX_TIME;
        this.active = true;
        this.sprite = new Sprite("/assets/note.png",384,90);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getTicksRemaining() {
        return ticksRemaining;
    }

    public String getClientName() {
        return clientName;
    }

    public void deliver(PizzaEntity pizza) {
        String txt;
        int color;
        if (pizza.getType() == type && ingredients.equals(pizza.getIngredients())) {
            txt = "Correct order!";
            color = 0x00FF00;
        } else {
            txt = "Wrong order!";
            color = 0xFF0000;
        }
        game.addMoney(50);
        game.showFadeOutText(txt, color, getX() + getWidth() / 2, getY() + getHeight() / 2 + 32);

        this.active = false;
    }

    @Override
    public void tick() {
        ticksRemaining--;

        if (ticksRemaining <= 0 && active) {
            active = false;
            game.showFadeOutText("Too late!", 0xFF0000, getX() + getWidth() / 2, getY() + getHeight() / 2 + 32);
        }
    }

    @Override
    public int getWidth() {
        return 384;
    }

    @Override
    public int getHeight() {
        return 96;
    }

    @Override
    public void draw(Graphics g) {
        if (!active) return;
        super.draw(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(Client.FONT.deriveFont(10f));

        g.setColor(Color.BLACK);
        g.drawString(type.getName(), getX() + 22, getY()+ 22);
        int x = getX() + 22;
        int y = getY() + 22;
        for (int i = 0; i < ingredients.size(); i++) {
            if (i % 3 == 0) {
                x = getX() + 22;
                y += 22;

            }
            Ingredient ing = ingredients.get(i);
            g.drawString(ing.getName(), x, y);
            x += g.getFontMetrics().stringWidth(ing.getName()) + 22;

        }
        g.setColor(new Color(217, 185, 120));
        g.fillRect(getX(), getHeight() - 18, getWidth(), 18);
        float p = (ticksRemaining / (float) MAX_TIME);
        g.setColor(new Color(1f-p, p, 0));
        g.fillRect(getX(), getHeight() - 18, (int)(getWidth() * p), 18);

    }
}
