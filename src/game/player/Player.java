package game.player;

import game.GameObject;
import game.GameWindow;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class Player extends GameObject {
    int hp;

    public Player() {
//        BufferedImage image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
//        renderer = new Renderer(image);
        renderer = new Renderer("assets/images/players/straight");
        hitBox = new BoxCollider(this, 32, 28);
        position.set(300, 500);
        hp = 100;
    }

    static Font font = new Font("Verdana", Font.BOLD, 32);
    @Override
    public void render(Graphics g) {
        super.render(g);

        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString(hp + ""
                , (int) position.x
                , (int) position.y);
    }

    @Override
    public void run() {
        super.run();
        move();
        limit();
        fire();
    }

    public void takeDamage(int damage){
        hp -= damage;
    }

    // TODO: remove fireCount
    int fireCount;
    private void fire() {
        fireCount++;
        if(GameWindow.isFirePress && fireCount > 20) {
            for (int i = 0; i < 1; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(position.x, position.y);
            }
            fireCount = 0;
        }
    }

    private void limit() {
        if(position.x < 0) {
            position.set(0, position.y);
        }
        if(position.x > Settings.BACKGROUND_WIDTH - Settings.PLAYER_WIDTH) {
            position.set(
                    Settings.BACKGROUND_WIDTH - Settings.PLAYER_WIDTH,
                    position.y
            );
        }
        if(position.y < 0) {
            position.set(position.x, 0);
        }
        if(position.y > Settings.GAME_HEIGHT - Settings.PLAYER_HEIGHT) {
            position.set(
                    position.x,
                    Settings.GAME_HEIGHT - Settings.PLAYER_HEIGHT
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
        velocity.set(vx, vy);
        velocity.setLength(playerSpeed);
    }

}
