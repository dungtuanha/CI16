package game.player;

import game.GameObject;
import game.Vector2D;
import game.enemy.Enemy;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    int count;

    public PlayerBullet() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        velocity.set(0, -5);
        count = 0;
    }

    @Override
    public void run() {
        super.run();
        count++;
        if(count > 120) {
            Enemy enemy = GameObject.find(Enemy.class);
            if(enemy != null) {
                Vector2D bulletToEnemy = enemy.position.clone();
                bulletToEnemy.substract(this.position);
                bulletToEnemy.setLength(5);

                this.velocity.set(bulletToEnemy);
            }
        }
    }
}
