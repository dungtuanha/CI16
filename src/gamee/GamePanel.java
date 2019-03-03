package gamee;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    Player player;
    Background background;
    Enemy enemy;


    public GamePanel() {
        player = new Player();
        background = new Background();
        enemy = new Enemy();
    }

    @Override
    public void paint(Graphics g) {
        background.render(g);

        player.render(g);

        enemy.render(g);
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
        background.run();
        player.run();
        enemy.run();
    }

}
