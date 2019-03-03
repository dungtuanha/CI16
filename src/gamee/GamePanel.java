package gamee;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    Vector2D playerPosition;
    BufferedImage backgroundImage;
    Vector2D backgroundPositon;
    BufferedImage bulletImage;
    ArrayList<Vector2D> bulletPositions;
    BufferedImage enemiesImage;
    Vector2D enemyPosition;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        playerPosition = new Vector2D(100, 100);
        backgroundPositon = new Vector2D(0, 600 - 3109);
        bulletImage = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        bulletPositions = new ArrayList<>();
        enemiesImage = SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png");
        enemyPosition = new Vector2D(0, 100);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(
                backgroundImage,
                (int) backgroundPositon.x,
                (int) backgroundPositon.y,
                null
        );
        g.drawImage(
                playerImage,
                (int) playerPosition.x,
                (int) playerPosition.y,
                null
        );

        for (int i = 0; i < bulletPositions.size() ; i++) {
            Vector2D bullPoosition = bulletPositions.get(i);
            g.drawImage(
                    bulletImage,
                    (int) bullPoosition.x,
                    (int) bullPoosition.y,
                    null
            );
        }

        g.drawImage(
                enemiesImage,
                (int) enemyPosition.x,
                (int) enemyPosition.y,
                null
        );
    }

    public void gameLoop(){
        long lastLoop = 0;
        long delay = 1000/60;
        while(true){
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastLoop > delay){
                runAll();       //logic game
                renderAll();    //render ảnh
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint();  // gọi lại hàm paint
    }

    private void runAll() {
        backgroundMove();
        playerMove();
        playerLimit();
        playerFire();
        bulletsRun();
        enemyMove();
    }


    boolean playerGoLeft = true;
    boolean playerGoRight = true;
    private void enemyMove() {
        if(enemyPosition.x == backgroundImage.getWidth()) {
            playerGoRight = false;
            playerGoLeft = true;
        } else if(enemyPosition.x == 0){
            playerGoLeft = false;
            playerGoRight = true;
        }

        if (playerGoRight == true) {
            enemyPosition.add(1, 0);
        } else{
            enemyPosition.add(-1,0);
        }
    }

    int frameCount;
    private void playerFire() {
        frameCount++;
        if (GameWindow.isFirePress && frameCount > 20) {
            Vector2D bulletPosition = playerPosition.clone();
            bulletPositions.add(bulletPosition);
            frameCount = 0;
        }
    }

    private void bulletsRun() {
        for (int i = 0; i < bulletPositions.size(); i++) {
            Vector2D bulletPosition = bulletPositions.get(i);
            bulletPosition.add(0, -3);
        }
    }

    private void playerLimit() {
        if(playerPosition.x < 0) {
            playerPosition.set(0, playerPosition.y);
        }

        if(playerPosition.x > backgroundImage.getWidth() - playerImage.getWidth()){
            playerPosition.set(
                    backgroundImage.getWidth() - playerImage.getWidth(),
                    playerPosition.y
            );
        }

        if (playerPosition.y < 0) {
            playerPosition.set(playerPosition.x, 0);
        }

        if (playerPosition.y > 600 - playerImage.getHeight()){
            playerPosition.set(
                    playerPosition.x,
                    600 - playerImage.getHeight()
            );
        }
    }

    private void playerMove() {
        int playerSpeed = 3;

        if(GameWindow.isUpPress){
            playerPosition.y -= playerSpeed;
        }
        if(GameWindow.isDownPress){
            playerPosition.y += playerSpeed;
        }
        if(GameWindow.isLeftPress){
            playerPosition.x -= playerSpeed;
        }
        if(GameWindow.isRightPress){
            playerPosition.x += playerSpeed;
        }
    }

    private void backgroundMove() {
        backgroundPositon.add(0, 10);
        if (backgroundPositon.y > 0) {
            backgroundPositon.set(backgroundPositon.x, 0);
        }
    }
}
