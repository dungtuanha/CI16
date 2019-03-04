package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    BufferedImage image;
    Vector2D position;
    ArrayList<PlayerBullet> bullets;

    public Player() {
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        position = new Vector2D(300, 500);
        bullets = new ArrayList<>();
    }

    public void render(Graphics g) {
        g.drawImage(
                image,
                (int) position.x,
                (int) position.y,
                null
        );
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.render(g);
        }
    }

    public void run() {
        move();
        limit();
        fire();
        bulletsRun();
    }

    // TODO: remove fireCount
    int fireCount;
    private void fire() {
        fireCount++;
        if(GameWindow.isFirePress && fireCount > 20) {
            for (int i = 0; i < 20; i++) {
                PlayerBullet bullet = new PlayerBullet();
                bullet.position.set(position.x, position.y);
                bullet.velocity.setAngle(-Math.PI / 3 - i * (Math.PI / 60));
                bullets.add(bullet);
            }
            fireCount = 0;
        }
    }

    private void limit() {
        if(position.x < 0) {
            position.set(0, position.y);
        }
        if(position.x > 384 - image.getWidth()) {
            position.set(
                    384 - image.getWidth(),
                    position.y
            );
        }
        if(position.y < 0) {
            position.set(position.x, 0);
        }
        if(position.y > 600 - image.getHeight()) {
            position.set(
                    position.x,
                    600 - image.getHeight()
            );
        }
    }

    private void move() {
        int playerSpeed = 3;
        int vx = 0;
        int vy = 0;
        if(GameWindow.isUpPress) {
            vy -= playerSpeed;
        }
        if(GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if(GameWindow.isLeftPress) {
            vx -= playerSpeed;
        }
        if(GameWindow.isRightPress) {
            vx += playerSpeed;
        }
        position.add(vx, vy);
    }

    private void bulletsRun() {
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.run();
        }
    }
}
