package game.enemy;

import game.GameObject;
import game.Settings;
import game.Settings;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject {
    ArrayList<EnemyBullet> enemyBullets;

    public Enemy() {
        renderer = new Renderer("assets/images/enemies/level0/blue");
        velocity.set(0, 3);
        enemyBullets = new ArrayList<>();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBullet enemyBullet = enemyBullets.get(i);
            enemyBullet.render(g);
        }
    }

    @Override
    public void run() {
        super.run();
        changeDirection();
        autoFire();
        bulletRun();
    }

    public void bulletRun() {
        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBullet enemyBullet = enemyBullets.get(i);
            enemyBullet.run();
        }
    }

    int fireCount;

    public void autoFire() {
        fireCount++;
        if (fireCount > 100) {
            EnemyBullet bullet = new EnemyBullet();
            bullet.position.set(position);
            enemyBullets.add(bullet);
            fireCount=0;
        }
    }

    private void changeDirection() {
        if (position.x > Settings.BACKGROUND_WIDTH - 28 && velocity.x > 0) {
            velocity.set(-velocity.x, velocity.y);
        }
        if (position.x < 0 && velocity.x < 0) {
            velocity.set(-velocity.x, velocity.y);
        }
    }
}