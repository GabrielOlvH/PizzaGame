package pizzagame;

import pizzagame.entity.Entity;
import pizzagame.entity.IngredientEntity;
import pizzagame.entity.PizzaEntity;
import pizzagame.entity.SauceJarEntity;
import pizzagame.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
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

        PizzaEntity e = new PizzaEntity();
        entities.add(e);
        e.setX(240);
        e.setY(640);

        for (int i = 0; i < Ingredient.ALL.length; i++) {
            Ingredient ing = Ingredient.ALL[i];
            int x = i % 8;
            int y = i / 8;
            entities.add(ing.createEntity(740+ x*140 - (ing.getSprite().getWidth()-96)/2, 600+ y*140 -(ing.getSprite().getHeight()-96)/2, 5));
        }

        SauceJarEntity cheeseJar = new SauceJarEntity(new Sprite("/assets/ingredients/cheese_sauce.png", 582 / 6, 924 / 6), PizzaType.CHEESE);
        cheeseJar.setX(120);
        cheeseJar.setY(380);
        entities.add(cheeseJar);

        SauceJarEntity tomatoJar = new SauceJarEntity(new Sprite("/assets/ingredients/tomato_sauce.png", 582 / 6, 924 / 6), PizzaType.TOMATO);
        tomatoJar.setX(260);
        tomatoJar.setY(380);
        entities.add(tomatoJar);

        SauceJarEntity chocolateJar = new SauceJarEntity(new Sprite("/assets/ingredients/chocolate_sauce.png", 582 / 6, 924 / 6), PizzaType.CHOCOLATE);
        chocolateJar.setX(400);
        chocolateJar.setY(380);
        entities.add(chocolateJar);
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
        entities.removeIf(Entity::isDiscarded);

    }
}

