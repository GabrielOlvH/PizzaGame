package pizzagame.listeners;

import pizzagame.Game;
import pizzagame.entity.DraggableEntity;
import pizzagame.entity.Entity;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GameMouseListener extends MouseInputAdapter {

    private final Game game;

    public GameMouseListener(Game game) {
        this.game = game;
    }

    private Point location;

    private DraggableEntity dragging;
    private MouseEvent pressed;

    public void mousePressed(MouseEvent me)
    {
        pressed = me;
        Entity entity = game.getEntities().stream()
                .filter(e -> me.getX() > e.getX() && me.getX() < e.getX() + e.getWidth() && me.getY() > e.getY() && me.getY() < e.getY() + e.getHeight())
                .findFirst()
                .orElse(null);

        if (entity instanceof DraggableEntity drag) {
            dragging = drag;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        super.mouseReleased(me);

        Entity other = game.getEntities().stream()
                .filter(e -> me.getX() > e.getX() && me.getX() < e.getX() + e.getWidth() && me.getY() > e.getY() && me.getY() < e.getY() + e.getHeight() && e != dragging)
                .findFirst()
                .orElse(null);

        if (dragging != null && other != null) {
            dragging.dropInto(other);
        }
        dragging = null;
    }

    public void mouseDragged(MouseEvent me) {
        if (dragging != null) {
            int x =  (me.getX() - pressed.getX()) ;
            int y =  (me.getY() - pressed.getY());
            dragging.setX(x);
            dragging.setY(y);
        }
    }
}