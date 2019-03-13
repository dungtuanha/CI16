package game;

import game.enemy.Enemy;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    Player player;
    Background background;
    ArrayList<Enemy> enemies;

    public GamePanel() {
        background = new Background();
        player = new Player();
        enemies = new ArrayList<>();
    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastLoop > delay) {
                runAll(); // logic game
                renderAll(); // render anh cua game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint(); // goi lai ham paint()
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if (object.active) {
                object.render(g);
            }
        }
    }

    private void runAll() {
        for (int i = 0; i < GameObject.objects.size() ; i++) {
            GameObject object = GameObject.objects.get(i);
            if (object.active) {
                object.run();
            }
        }
        summonEnemies();
    }


    // TODO: remove summonCount
    int summonCount;
    int wayCount;
    int enemyCount;
    Random random = new Random();
    int enemyX = 100 + random.nextInt(200);
    private void summonEnemies() {
        wayCount++;
        if(wayCount > 120) {
            summonCount++;
            if(summonCount > 15) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(enemyX, -100);
                enemy.velocity.setAngle(Math.PI / 9);
                enemies.add(enemy);
                enemyCount++;
                summonCount = 0;
                if(enemyCount > 4) {
                    wayCount = 0;
                    enemyCount = 0;
                    enemyX = 100 + random.nextInt(200);
                }
            }
        }
    }
}
