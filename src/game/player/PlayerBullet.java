package game.player;

import game.GameObject;
import game.Vector2D;
import game.enemy.Enemy;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    int count;

    public PlayerBullet() {
//        BufferedImage image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
//        renderer = new Renderer(image);
        renderer = new Renderer("assets/images/player-bullets/a");
        velocity.set(0, -5);
        count = 0;
    }

    @Override
    public void run() {
        super.run();
    }
}
