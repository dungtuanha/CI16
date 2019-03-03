package gamee;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    BufferedImage backgroundImage;
    Vector2D backgroundPositon;

    public Background(){
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        backgroundPositon = new Vector2D(0, 600 - 3109);
    }

    public void render(Graphics g){
            g.drawImage(
                    backgroundImage,
                    (int) backgroundPositon.x,
                    (int) backgroundPositon.y,
                    null
            );
    }

    public void run(){
        backgroundMove();
    }

    private void backgroundMove() {
        backgroundPositon.add(0, 10);
        if (backgroundPositon.y > 0) {
            backgroundPositon.set(backgroundPositon.x, 0);
        }
    }
}
