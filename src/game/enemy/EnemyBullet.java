package game.enemy;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {
    int firecount;
    int dame;

    public EnemyBullet() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        renderer = new Renderer(image);
        velocity.set(0, 3);
        firecount = 0;
        dame = 1;
        hitBox = new BoxCollider(this, 24, 24);
    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
        checkIntersects();
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT +50){
            this.deactive();
        }
    }

    private void checkIntersects() {
        Player player = GameObject.findIntersects(Player.class, this);
        if (player != null){
            this.deactive();
            player.takeDamage(dame);
        }
    }
}