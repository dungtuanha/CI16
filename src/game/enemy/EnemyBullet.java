package game.enemy;

import game.GameObject;
import game.Settings;
import game.Vector2D;
import game.player.Player;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    int count;

    public EnemyBullet() {
        image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        velocity.set(0, 3);
    }

    @Override
    public void run() {
        super.run();
       count++;
        if (count > 150) {
            Player clone = GameObject.find(Player.class);
            if (clone != null) {
                Vector2D bulletToPlayer = clone.position.clone();
                bulletToPlayer.substract(this.position);
                bulletToPlayer.setLength(3);
                this.velocity.set(bulletToPlayer);
          }
        }
    }
}