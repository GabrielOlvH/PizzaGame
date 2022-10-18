package pizzagame.entity;

import pizzagame.Sprite;

import java.awt.*;

public class Entity {
    private int x, y;

    protected Sprite sprite;

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
}
