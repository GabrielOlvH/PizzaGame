package pizzagame.entity;

import pizzagame.Game;
import pizzagame.Sprite;

import java.awt.*;

public class Entity {
    protected int x, y;
    protected int z; //used for rendering order

    protected Sprite sprite;

    public boolean discarded;

    protected Game game;

    public Entity(Game game) {
        this.game = game;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public void discard() {
        this.discarded = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getWidth() {
        return sprite.getWidth();
    }

    public int getHeight() {
        return sprite.getHeight();
    }

    public void draw(Graphics g) {
        if (sprite != null)
            g.drawImage(sprite.getImage(), x, y, sprite.getWidth(), sprite.getHeight(), null);
    }

    public void drawMouseOver(Graphics g, Point p) {

    }

    public void tick() {

    }

    public void onClick(int x, int y) {

    }

    public boolean isPointOver(int x, int y) {
        return x > this.x && x < this.x + getWidth() && y > this.y && y < this.y + getHeight();
    }
}
