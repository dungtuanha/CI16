package gamee;

import java.awt.*;

public class Program {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        window.setSize(800, 600);
        window.setTitle("Game Toubor");
        window.setResizable(false);

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.CYAN);

        window.add(panel);

        window.setVisible(true);

        panel.gameLoop();

//        ArrayList<gamee.Vector2D> vectors = new ArrayList<>();
//        vectors.add(new gamee.Vector2D(1, 1));
//        vectors.add(new gamee.Vector2D(3, 7));
//        vectors.add(new gamee.Vector2D(3, 1));

//        for (gamee.Vector2D vector : vectors) {
//        }

//        gamee.Vector2D maxLengthVector = null;
//        double maxLength = 0;
//        for (int i = 0; i < vectors.size(); i++) {
//            gamee.Vector2D vector = vectors.get(i);
//            if(vector.getLength() > maxLength) {
//                maxLengthVector = vector;
//                maxLength = vector.getLength();
//            }
//        }
//        System.out.println(maxLengthVector.x + " " + maxLengthVector.y);
    }

}


