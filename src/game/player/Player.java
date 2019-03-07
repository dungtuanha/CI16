package game.player;

import game.GameObject;
import game.GameWindow;
import game.Settings;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class Player extends GameObject {
    ArrayList<PlayerBullet> bullets;

    public Player() {
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
//        position = new Vector2D(300, 500);
        position.set(Settings.PLAYER_POSITION.x, Settings.PLAYER_POSITION.y);
        bullets = new ArrayList<>();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.render(g);
        }
    }

    @Override
    public void run() {
        super.run();
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
        if(position.x > Settings.BACKGROUND_WIDTH - image.getWidth()) {
            position.set(
                    Settings.BACKGROUND_WIDTH - image.getWidth(),
                    position.y
            );
        }
        if(position.y < 0) {
            position.set(position.x, 0);
        }
        if(position.y > Settings.GAME_HEIGHT - image.getHeight()) {
            position.set(
                    position.x,
                    Settings.GAME_HEIGHT - image.getHeight()
            );
        }
    }

    private void move() {
        Settings.PLAYER_MOVE.set(0,0);

        if(GameWindow.isUpPress) {
            Settings.PLAYER_MOVE.y -= Settings.PLAYER_SPEED;
        }
        if(GameWindow.isDownPress) {
            Settings.PLAYER_MOVE.y += Settings.PLAYER_SPEED;
        }
        if(GameWindow.isLeftPress) {
            Settings.PLAYER_MOVE.x -= Settings.PLAYER_SPEED;
        }
        if(GameWindow.isRightPress) {
            Settings.PLAYER_MOVE.x += Settings.PLAYER_SPEED;
        }
//        position.add(vx, vy);
        velocity.set(Settings.PLAYER_MOVE.x, Settings.PLAYER_MOVE.y);
        velocity.setLength(Settings.PLAYER_SPEED);
    }

    private void bulletsRun() {
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.run();
        }
    }
}
