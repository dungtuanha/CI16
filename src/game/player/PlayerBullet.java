package game.player;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.enemy.Enemy;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    int count;

    public PlayerBullet() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
//        velocity = new Vector2D(0, -5);
        velocity.set(0, - Settings.BULLET_SPEED );
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (count > 120) {
            Enemy enemy = GameObject.find(Enemy.class);
            if (enemy != null) {
                Vector2D bulletToEnemy = enemy.position.clone();
                bulletToEnemy.substract(this.position);
                bulletToEnemy.setLength(Settings.BULLET_SPEED);

                this.velocity.set(bulletToEnemy);
            }
        }
    }
}