package pizzagame.entity;

import pizzagame.Game;
import pizzagame.Sprite;

import java.awt.*;

public class TrashCanEntity extends Entity{
    public TrashCanEntity(Game game) {
        super(game);
        this.sprite = new Sprite("/assets/trashCan.png",(int)(160*.66),(int)(160*.66));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
