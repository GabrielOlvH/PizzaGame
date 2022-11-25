package pizzagame;

import pizzagame.entity.*;
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

        TrashCanEntity trashCan = new TrashCanEntity();
        trashCan.setX(200);
        trashCan.setY(600);
        entities.add(trashCan);

        for (int i = 0; i < Ingredient.ALL.length; i++) {
            Ingredient ing = Ingredient.ALL[i];
            int x = i % 8;
            int y = i / 8;
            entities.add(ing.createEntity(740+ x*140 - (ing.getSprite().getWidth()-96)/2, 790+ y*140 -(ing.getSprite().getHeight()-96)/2, 5));
        }

        SauceJarEntity cheeseJar = new SauceJarEntity(new Sprite("/assets/ingredients/cheese_sauce.png", 582 / 6, 924 / 6), PizzaType.CHEESE);
        cheeseJar.setX(20);
        cheeseJar.setY(680);
        entities.add(cheeseJar);

        SauceJarEntity tomatoJar = new SauceJarEntity(new Sprite("/assets/ingredients/tomato_sauce.png", 582 / 6, 924 / 6), PizzaType.TOMATO);
        tomatoJar.setX(20);
        tomatoJar.setY(800);
        entities.add(tomatoJar);

        SauceJarEntity chocolateJar = new SauceJarEntity(new Sprite("/assets/ingredients/chocolate_sauce.png", 582 / 6, 924 / 6), PizzaType.CHOCOLATE);
        chocolateJar.setX(20);
        chocolateJar.setY(920);
        entities.add(chocolateJar);
    }

    public void addPizzaDough() {
        if (entities.stream().noneMatch(e -> e instanceof PizzaEntity)) {
            PizzaEntity e = new PizzaEntity();
            entities.add(1, e);
            e.setX(240);
            e.setY(800);
        }
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

