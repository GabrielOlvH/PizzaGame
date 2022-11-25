package pizzagame.entity;

import pizzagame.Sprite;

import java.awt.*;

public class TrashCanEntity extends Entity{
    public TrashCanEntity(){
        this.sprite = new Sprite("/assets/trashCan.png",160,160);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
