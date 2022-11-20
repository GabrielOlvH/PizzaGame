package pizzagame.entity;

public class OvenEntity extends Entity {
    private boolean isUnlocked;
    private PizzaEntity cooking;

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public PizzaEntity getCooking() {
        return cooking;
    }

    public void setCooking(PizzaEntity cooking) {
        this.cooking = cooking;
    }
}
