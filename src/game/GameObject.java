package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    // Quản lí đối tượng
    public static ArrayList<GameObject> objects = new ArrayList<>();

    // find
    public static <E extends GameObject> E find(Class<E> cls) {
        // lướt qua mảng objects
        // kiểm tra từng phần tử
        // nếu phần tử thóa mãn >> return
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object.getClass().isAssignableFrom(cls)) {
                return (E) object;
            }
        }
        return null;
    }

    // Định nghĩa đối tượng
    public BufferedImage image;
    public Vector2D position;
    public Vector2D velocity;

    public GameObject() {
        position = new Vector2D();
        velocity = new Vector2D();
    }

    public void render(Graphics g) {
        g.drawImage(
                image,
                (int) position.x,
                (int) position.y,
                null
        );
    }

    public void run() {
        position.add(velocity.x, velocity.y);
    }
}
