package game.enemy;

import game.GameObject;
import game.Settings;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class Enemy extends GameObject {
    ArrayList<EnemyBullet> enemyBullets;
    int hp;
    int damage;

    public Enemy() {
        renderer = new Renderer("assets/images/enemies/level0/blue");
        velocity.set(0,3);
        enemyBullets = new ArrayList<>();
        hitBox = new BoxCollider(this, 28, 28);
        hp = 3;
        damage = 1;
    }

    static Font font = new Font("Verdana", Font.BOLD, 32);
    @Override
    public void render(Graphics g) {
        super.render(g);

        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBullet enemyBullet = enemyBullets.get(i);
            enemyBullet.render(g);
        }

        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString(hp + ""
        , (int) position.x
        , (int) position.y);
    }

    @Override
    public void run() {
        super.run();
        changeDirection();
        autoFire();
        bulletRun();
        deactiveIfNeeded();
        checkIntersects();
    }

    public void checkIntersects() {
        Player player = GameObject.findIntersects(Player.class
                , this);
        if(player != null) {
            this.deactive();
            player.takeDamage(damage);
        }
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT + 50){
            this.deactive();
        }
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
        if (fireCount > 120) {
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(this.position);
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

    public void takeDamage(int damage){
        hp -= damage;
        if (hp <=0) {
            hp = 0;
            this.deactive();
        }
    }

    @Override
    public void reset() {
        super.reset();
        hp = 3;
    }
}