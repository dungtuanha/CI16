package game.enemy;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {
    int firecount;

    public EnemyBullet() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        renderer = new Renderer(image);
        velocity.set(0, 3);
        firecount = 0;
    }

    @Override
    public void run() {
        super.run();
    }
}