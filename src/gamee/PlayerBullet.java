package gamee;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet {
    BufferedImage image;
    Vector2D position;

    public PlayerBullet(){
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        position = new Vector2D();
    }

    public void render(Graphics g){
        g.drawImage(
                image,
                (int) position.x,
                (int) position.y,
                null
        );
    }

    public void run(){
        position.add(0,-5);
    }
}
