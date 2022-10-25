package pizzagame.entity;

public abstract class DraggableEntity extends Entity {

    private int originalX, originalY;

    public abstract boolean dropInto(Entity entity);

    public void setOriginalX(int originalX) {
        this.originalX = originalX;
    }

    public int getOriginalX() {
        return originalX;
    }

    public void setOriginalY(int originalY) {
        this.originalY = originalY;
    }

    public int getOriginalY() {
        return originalY;
    }
}
