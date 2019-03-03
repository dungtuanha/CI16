package gamee;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    BufferedImage enemiesImage;
    ArrayList<Vector2D> enemyPositions;

    public Enemy(){
        enemiesImage = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
        enemyPositions = new ArrayList<>();
    }

    public void render(Graphics g){
        for (int i = 0; i < enemyPositions.size(); i++) {
            Vector2D enemyPosition = enemyPositions.get(i);
            g.drawImage(
                    enemiesImage,
                    (int) enemyPosition.x,
                    (int) enemyPosition.y,
                    null
            );

        }
    }

    public void run(){
        enemyRun();
        summonEnemies();
    }

    int summonCount;
    int wayCount;
    int enemyCount;
    Random random = new Random();
    int enemyX = 100 + random.nextInt(200);
    private void summonEnemies() {
        wayCount++;
        if(wayCount > 120) {
            summonCount++;
            if(summonCount > 15){
                Vector2D enemyPosition = new Vector2D(
                        enemyX,
                        -100
                );
                enemyPositions.add(enemyPosition);
                summonCount = 0;
                enemyCount++;
                if (enemyCount > 4){
                    wayCount = 0;
                    enemyCount = 0;
                    enemyX = 100 + random.nextInt(200);
                }
            }
        }
    }

    private void enemyRun() {
        for (int i = 0; i < enemyPositions.size() ; i++) {
            Vector2D enemyPosition = enemyPositions.get(i);
            enemyPosition.add(0, 3);
        }
    }
}
