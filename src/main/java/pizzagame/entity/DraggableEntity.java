package pizzagame.entity;

import pizzagame.Game;

public abstract class DraggableEntity extends Entity {

    private int originalX, originalY;

    public DraggableEntity(Game game) {
        super(game);
    }

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
