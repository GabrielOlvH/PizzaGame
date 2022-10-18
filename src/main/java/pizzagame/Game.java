package pizzagame;

import pizzagame.entity.Entity;
import pizzagame.entity.IngredientEntity;
import pizzagame.entity.PizzaEntity;
import pizzagame.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Client client;
    private Random random;
    private GameScreen screen;
    private List<Entity> entities;

    public Game(Client client, String playerName) {
        this.client = client;
        this.random = new Random();
        this.screen = new GameScreen(this);
        this.entities = new ArrayList<>();

        entities.add(new IngredientEntity("Mushroom", new Sprite("/assets/ingredients/mushroom.png", 256, 256)));
        PizzaEntity e = new PizzaEntity();
        entities.add(e);
        e.setX(500);
        e.setY(500);
    }

    public Client getClient() {
        return client;
    }

    public Random getRandom() {
        return random;
    }

    public GameScreen getScreen() {
        return screen;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void tick() {


    }
}

