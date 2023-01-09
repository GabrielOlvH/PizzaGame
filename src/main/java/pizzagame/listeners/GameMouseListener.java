package pizzagame.listeners;

import pizzagame.Game;
import pizzagame.entity.DraggableEntity;
import pizzagame.entity.Entity;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public class GameMouseListener extends MouseInputAdapter {

    private final Game game;

    private DraggableEntity dragging;
    private MouseEvent pressed;
    private int prevZ;

    public GameMouseListener(Game game) {
        this.game = game;
    }


    public void mousePressed(MouseEvent me)
    {
        pressed = me;
        Entity entity = game.getEntities().stream()
                .filter(e -> me.getX() > e.getX() && me.getX() < e.getX() + e.getWidth() && me.getY() > e.getY() && me.getY() < e.getY() + e.getHeight())
                .sorted(Comparator.comparingInt(e -> -e.getZ()))
                .findFirst()
                .orElse(null);

        if (entity != null)
            entity.onClick(me.getX(), me.getY());

        if (entity instanceof DraggableEntity drag && drag.canDrag()) {
            dragging = drag;
            prevZ = dragging.getZ();
            dragging.setZ(10); // render dragging entity on top of others
            dragging.setOriginalX(drag.getX());
            dragging.setOriginalY(drag.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        super.mouseReleased(me);

        Entity other = game.getEntities().stream()
                .filter(e -> me.getX() > e.getX() && me.getX() < e.getX() + e.getWidth() && me.getY() > e.getY() && me.getY() < e.getY() + e.getHeight() && e != dragging)
                .findFirst()
                .orElse(null);

        if (dragging != null) {
            dragging.setZ(prevZ);
            if (!dragging.dropInto(other)) {
                dragging.setX(dragging.getOriginalX());
                dragging.setY(dragging.getOriginalY());
            }
        }
        dragging = null;
    }

    public void mouseDragged(MouseEvent me) {
        if (dragging != null) {
            int x = dragging.getOriginalX() + (me.getX() - pressed.getX());
            int y = dragging.getOriginalY() + (me.getY() - pressed.getY());
            dragging.setX(x);
            dragging.setY(y);
        }
    }
}
