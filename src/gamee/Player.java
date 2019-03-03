package gamee;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    BufferedImage image;
    Vector2D position;
    ArrayList<PlayerBullet> bullets;

    public Player(){
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        position = new Vector2D(300, 500);
        bullets = new ArrayList<>();
    }

    public void render(Graphics g){
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

    int frameCount;
    private void fire() {
        frameCount++;
        if (GameWindow.isFirePress && frameCount > 20) {
//            Vector2D bulletPosition = position.clone();
//            bulletPositions.add(bulletPosition);
            PlayerBullet bullet = new PlayerBullet();
            bullet.position.set(position.x, position.y);
            bullets.add(bullet);
            frameCount = 0;
        }
    }

    private void limit() {
        if(position.x < 0) {
            position.set(0, position.y);
        }

        if(position.x > 384 - image.getWidth()){
            position.set(
                    384 - image.getWidth(),
                    position.y
            );
        }

        if (position.y < 0) {
            position.set(position.x, 0);
        }

        if (position.y > 600 - image.getHeight()){
            position.set(
                    position.x,
                    600 - image.getHeight()
            );
        }
    }

    private void move() {
        int playerSpeed = 3;

        if(GameWindow.isUpPress){
            position.y -= playerSpeed;
        }
        if(GameWindow.isDownPress){
            position.y += playerSpeed;
        }
        if(GameWindow.isLeftPress){
            position.x -= playerSpeed;
        }
        if(GameWindow.isRightPress){
            position.x += playerSpeed;
        }
    }

    private void bulletsRun() {
        for (int i = 0; i < bullets.size() ; i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.run();
        }
    }
}
