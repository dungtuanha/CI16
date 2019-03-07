package game.enemy;

import game.GameObject;
import game.Settings;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject {

    public Enemy() {
        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png");
//        velocity = new Vector2D(0, 3);
        velocity.set(0, 3);
    }

    @Override
    public void run() {
        super.run();
        changeDirection();
    }

    private void changeDirection() {
        if(position.x > Settings.BACKGROUND_WIDTH - 28 && velocity.x > 0) {
            velocity.set(-velocity.x, velocity.y);
        }
        if(position.x < 0 && velocity.x < 0) {
            velocity.set(-velocity.x, velocity.y);
        }
    }
}
