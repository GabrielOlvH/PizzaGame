package pizzagame;

import pizzagame.entity.*;
import pizzagame.screens.GameScreen;

import java.util.*;

public class Game {

    private Client client;
    private Random random;
    private GameScreen screen;
    private List<Entity> entities;
    private Map<Ingredient, Integer> availableIngredients = new HashMap<>();
    private int ingredientsPage = 0;

    private int money;

    public Game(Client client, String playerName) {
        this.client = client;
        this.random = new Random();
        this.screen = new GameScreen(this);
        this.entities = new ArrayList<>();

        initEntities();
        initIngredients();

    }

    private void initEntities() {
        TrashCanEntity trashCan = new TrashCanEntity(this);
        trashCan.setX(200);
        trashCan.setY(600);
        entities.add(trashCan);

        SauceJarEntity cheeseJar = new SauceJarEntity(this, new Sprite("/assets/ingredients/cheese_sauce.png", 582 / 6, 924 / 6), PizzaType.CHEESE);
        cheeseJar.setX(20);
        cheeseJar.setY(680);
        entities.add(cheeseJar);

        SauceJarEntity tomatoJar = new SauceJarEntity(this, new Sprite("/assets/ingredients/tomato_sauce.png", 582 / 6, 924 / 6), PizzaType.TOMATO);
        tomatoJar.setX(20);
        tomatoJar.setY(800);
        entities.add(tomatoJar);

        SauceJarEntity chocolateJar = new SauceJarEntity(this, new Sprite("/assets/ingredients/chocolate_sauce.png", 582 / 6, 924 / 6), PizzaType.CHOCOLATE);
        chocolateJar.setX(20);
        chocolateJar.setY(920);
        entities.add(chocolateJar);

        for (int i = 0; i < 4; i++) {
            OrderEntity order = new OrderEntity(this);
            order.setX(i * (order.getWidth() + 32));
            entities.add(order);
        }

        for (int i = 0; i < 16; i++) {
            int x = i % 8;
            int y = i / 8;
            IngredientEntity entity = new IngredientEntity(this);
            entity.setX(740 + x * 128);
            entity.setY(790 + y * 130 + (random.nextInt(10) - 20));
            entities.add(entity);
        }

    }

    public void initIngredients() {
        for (Ingredient ingredient : Ingredient.ALL) {
            availableIngredients.put(ingredient, 5);
        }

        updateIngredients();
    }

    public void updateIngredients() {
        Iterator<Map.Entry<Ingredient, Integer>> it = availableIngredients.entrySet().iterator();
        if (availableIngredients.size() > ingredientsPage * 16) {
            for (int i = 0; i < ingredientsPage * 16; i++) {
                it.next();
            }
        }
        entities.stream().filter(e -> e instanceof IngredientEntity).forEach(e -> {
            IngredientEntity entity = (IngredientEntity) e;
            if (it.hasNext()) {
                Map.Entry<Ingredient, Integer> entry = it.next();
                entity.setIngredient(entry.getKey());
            } else {
                entity.setIngredient(null);
            }
        });
    }

    public void nextPage() {
        if (this.ingredientsPage < this.availableIngredients.size()/16) {
            this.ingredientsPage++;
            this.updateIngredients();
        }
    }

    public void prevPage() {
        if (ingredientsPage > 0) {
            this.ingredientsPage--;
            this.updateIngredients();
        }
    }

    public List<OrderEntity> getActiveOrders() {
        List<OrderEntity> orders = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof OrderEntity order && order.isActive()) orders.add(order);
        }
        return orders;
    }

    public void addRandomOrder() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Ingredient> shuffled = new ArrayList<>(List.of(Ingredient.ALL));
        Collections.shuffle(shuffled);
        for (int i = 0; i < 6; i++) {
            ingredients.add(shuffled.get(i));
        }
        PizzaType type = PizzaType.VALID_TYPES[random.nextInt(3)];
        entities.stream().filter((o) -> o instanceof OrderEntity order && !order.isActive()).findFirst().ifPresent(e -> {
            OrderEntity order = (OrderEntity) e;
            order.setInfo(type, ingredients);
        });

    }

    public void addPizzaDough() {
        if (entities.stream().noneMatch(e -> e instanceof PizzaEntity)) {
            PizzaEntity e = new PizzaEntity(this);
            entities.add(1, e);
            e.setX(240);
            e.setY(800);
        }
    }

    public Map<Ingredient, Integer> getAvailableIngredients() {
        return availableIngredients;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
        FadeOutTextEntity txt = new FadeOutTextEntity(this, "+" + money, 0x00FF00);
        txt.setX(1650);
        txt.setY(200);
        entities.add(txt);
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
        List<OrderEntity> activeOrders = getActiveOrders();
        if (activeOrders.size() < 4 && random.nextFloat() > 0.97) {
            addRandomOrder();
        }

        for (Entity e : new ArrayList<>(entities)) {
            e.tick();
        }

        entities.removeIf(Entity::isDiscarded);
    }
}

