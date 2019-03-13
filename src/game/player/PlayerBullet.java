package game.player;

import game.GameObject;
import game.Vector2D;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    int count;
    int damage;

    public PlayerBullet() {
//        BufferedImage image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
//        renderer = new Renderer(image);
        renderer = new Renderer("assets/images/player-bullets/a");
        velocity.set(0, -5);
        count = 0;
        hitBox = new BoxCollider(this, 24, 24);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        checkDeactiveIfNeeded();
        checkIntersects();
    }

    private void checkIntersects() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this);
        if (enemy != null){
            this.deactive();
//            enemy.deactive();
            enemy.takeDamage(damage);
        }
    }

    private void checkDeactiveIfNeeded() {
        if (this.position.y < -30){
            this.deactive();
        }
    }
}
