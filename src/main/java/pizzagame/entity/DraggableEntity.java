package pizzagame.entity;

public abstract class DraggableEntity extends Entity {

    private int originalX, originalY;

    public abstract boolean dropInto(Entity entity);
}
