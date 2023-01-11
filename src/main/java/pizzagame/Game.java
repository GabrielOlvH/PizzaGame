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
    private Statistics statistics = new Statistics();
    private int ingredientsPage = 0;
    private boolean shopMode = false;
    private int money;
    private int day;
    private int month;

    private List<Boolean> ordersDelivered = new ArrayList<>();

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
        trashCan.setX((int)(200*.66));
        trashCan.setY((int)(600*.66));
        entities.add(trashCan);

        SauceJarEntity cheeseJar = new SauceJarEntity(this, new Sprite("/assets/ingredients/cheese_sauce.png", (int)((582 / 6)*.66),(int)((924 / 6)*.66)), PizzaType.CHEESE);
        cheeseJar.setX((int)(20*.66));
        cheeseJar.setY((int)(680*.66));
        entities.add(cheeseJar);

        SauceJarEntity tomatoJar = new SauceJarEntity(this, new Sprite("/assets/ingredients/tomato_sauce.png", (int)((582 / 6)*.66), (int)((924 / 6)*.66)), PizzaType.TOMATO);
        tomatoJar.setX((int)(20*.66));
        tomatoJar.setY((int)(800*.66));
        entities.add(tomatoJar);

        SauceJarEntity chocolateJar = new SauceJarEntity(this, new Sprite("/assets/ingredients/chocolate_sauce.png", (int)((582 / 6)*.66), (int)((924 / 6)*.66)), PizzaType.CHOCOLATE);
        chocolateJar.setX((int)(20*.66));
        chocolateJar.setY((int)(920*.66));
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
            entity.setX((int)((740 + x * 128)*.66));
            entity.setY((int)((790 + y * 130 + (random.nextInt(10) - 20))*.66));
            entities.add(entity);
        }

        OvenEntity oven = new OvenEntity(this);
        oven.setPos((int)(790*.66), (int)(580*.66));
        entities.add(oven);

        OvenEntity secondOven = new OvenEntity(this);
        secondOven.setUnlocked(false);
        secondOven.setPos((int)((790*.66 + oven.getWidth() + 16)), (int)(580*.66));
        entities.add(secondOven);

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
        if (entities.stream().noneMatch(e -> e instanceof PizzaEntity pizza && pizza.getCookingTime() == 0)) {
            PizzaEntity e = new PizzaEntity(this);
            e.setPos((int)(240*.66f), (int)(800*.66f));
            entities.add(e);
        }
    }

    public void showFadeOutText(String text, int color, int x, int y) {
        FadeOutTextEntity txt = new FadeOutTextEntity(this, text, color);
        txt.setPos(x, y);
        entities.add(txt);
    }

    public Map<Ingredient, Integer> getAvailableIngredients() {
        return availableIngredients;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
        statistics.moneyGained += money;
        showFadeOutText("+" + money, 0x00FF00, (int)(1650*.66), (int)(200*.66));
    }

    public void takeMoney(int money) {
        this.money -= money;
        statistics.moneySpent += money;
        showFadeOutText("-" + money, 0xFF0000, (int)(1650*.66), (int)(200*.66));
    }

    public Statistics getStatistics() {
        return statistics;
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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public boolean isShopMode() {
        return shopMode;
    }

    public void toggleShopMode() {
        this.shopMode = !this.shopMode;
    }

    public List<Boolean> getOrdersDelivered() {
        return ordersDelivered;
    }

    public int getReputation() {
        if (ordersDelivered.size() < 10) {
            return 5;
        } else {
            int t = 0;
            for (int i = ordersDelivered.size() - 10; i < ordersDelivered.size(); i++) {
                if (ordersDelivered.get(i)) t++;
            }

            return (t/10);
        }
    }

    public void tick() {
        statistics.runTicks++;
        int dayProgress = statistics.runTicks % (20 * 60 * 10);
        if (dayProgress == 0) {
            day++;
            if (day == 30) {
                month++;
                day = 0;
                getActiveOrders().forEach(OrderEntity::expire);
            }
            StatisticsDisplayEntity e = new StatisticsDisplayEntity(this);
            e.setX((int)(1920*.66));
            e.setY((int)(250*.66));
            entities.add(e);
        }
        int maxOrders;
        int rep = getReputation();
        if (rep > 8) maxOrders = 4;
        else if (rep > 5) maxOrders = 3;
        else if (rep > 2) maxOrders = 2;
        else maxOrders = 1;

        List<OrderEntity> activeOrders = getActiveOrders();
        if (activeOrders.size() < maxOrders && random.nextFloat() > 0.97 && dayProgress < 20 * 60 * 9) {
            addRandomOrder();
        }

        for (Entity e : new ArrayList<>(entities)) {
            e.tick();
        }

        entities.removeIf(Entity::isDiscarded);
    }
}

